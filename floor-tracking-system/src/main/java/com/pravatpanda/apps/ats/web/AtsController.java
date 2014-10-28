package com.pravatpanda.apps.ats.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pravatpanda.apps.ats.bi.AtsBiService;
import com.pravatpanda.apps.ats.bi.AtsException;
import com.pravatpanda.apps.ats.bi.ExcelExporter;
import com.pravatpanda.apps.ats.bi.ExcelUIExporter;
import com.pravatpanda.apps.ats.domain.AssociateInfo;
import com.pravatpanda.apps.ats.domain.AssociateInfoMin;
import com.pravatpanda.apps.ats.domain.FloorLayout;
import com.pravatpanda.apps.ats.domain.ODC;
import com.pravatpanda.apps.ats.domain.ui.ChooseFloorVO;
import com.pravatpanda.apps.ats.domain.ui.NewOdcVO;
import com.pravatpanda.apps.ats.web.validator.AssociateValidator;
import com.pravatpanda.apps.ats.web.validator.ODCValidator;

@Controller
public class AtsController {
	
	@Autowired
	AtsBiService sevice; 
	@Autowired
	ODCValidator validator; 
	@Autowired
	AssociateValidator aValidator;
	

	/**
	 * STARTING POINT OF THE APPLICATION
	 */
	@RequestMapping(value={"/ats/chooseFloor.ats"})
	public ModelAndView showChooseFloor(@RequestParam (value="message", required=false) String message,
										HttpServletRequest request, 
										HttpServletResponse response) {

		Map<String, String> floors = sevice.getAvailableFloors();
		ModelAndView view = new ModelAndView("chooseFloor");
		view.addObject("alldata", floors);
		view.addObject("map", new ODC());
		if(StringUtils.isNotBlank(message))
			view.addObject("message", message);
		request.getSession(true).removeAttribute("floorname");
		return view;
	}


	@RequestMapping("/ats/showFloorDetails.ats")
	public ModelAndView showFloorLayout(@ModelAttribute ChooseFloorVO odc, 
			@RequestParam (value="message", required=false) String message,
			@RequestParam (value="cubeId", required=false) String cubeId,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		// get the floor id from the request. If not present in the request, get it from session
		String floorId = odc.getOdcId();
		if(StringUtils.isEmpty(floorId)) {
			floorId = getFloorId(request);
		}
		FloorLayout floorPlan = sevice.getFloorPlan(floorId);
		setSession(floorId, request, floorPlan);
		
		int hitCount = retrieveHitCount(floorId, request);
		
		request.setAttribute("FLOOR_LAYOUT", floorPlan);
		ModelAndView view = new ModelAndView("AssociateTracker");
		if(StringUtils.isNotBlank(message))		view.addObject("message", message);
		if(StringUtils.isNotBlank(cubeId))		view.addObject("cubeId", cubeId);
		view.addObject("hitcount",  hitCount);
		return view;
	}
	
	private int retrieveHitCount(String floorId,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);
		session.setAttribute("FLOOR_ID", floorId);
		int hitCount = sevice.retrieveHitCount(floorId);
		
		return hitCount;
	}
	
	private void setSession(String floorId, HttpServletRequest request, FloorLayout floorPlan) {

		if(StringUtils.isNotEmpty(floorId)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("FLOOR_ID", floorId);
			session.setAttribute("floorname", floorPlan.getFloorName());
		} else {
			throw new AtsException("SessionExpired", "Could not find floor id in session. Session might be invalid");
		}
	}

	private String getFloorId(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String floorId = (String) session.getAttribute("FLOOR_ID");
		if(null != floorId)
			return floorId;
		else 
			throw new AtsException("SessionExpired", "Could not find floor id in session. Session might be invalid");
	}

	@RequestMapping("/ats/searchresults.ats")
	public ModelAndView searchResults(@RequestParam(value="name", required=false) String name, 
			@RequestParam(value="empid", required=false) Integer empId, 
			@RequestParam(value="assetId", required=false) String assetId, 
			@RequestParam(value="cubeId", required=false) String cubeId, 
			@RequestParam(value="projName", required=false) String projName, 
			@RequestParam(value="shift", required=false) String shift, 
			@RequestParam(value="viewdetail", required=false) Boolean showFullDetails, 
			@RequestParam(value="source", required=false) String source, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		if(StringUtils.isEmpty(name)) {
			name = null;
		}
		if(StringUtils.isEmpty(assetId)) {
			assetId = null;
		}
		if(StringUtils.isEmpty(cubeId)) {
			cubeId = null;
		}
		if(StringUtils.isEmpty(projName)) {
			projName = null;
		}
		if(StringUtils.isEmpty(shift)) {
			shift = null;
		}
		
		int empIdBasic = 0;
		if(null != empId) {
			empIdBasic = empId.intValue();
		}
		boolean fullDetails = false;
		if(null != showFullDetails) {
			fullDetails = showFullDetails.booleanValue();
		}
		ModelAndView view = new ModelAndView("searchResults");
		List<AssociateInfoMin> list = sevice.searchEmployee(cubeId, name, empIdBasic, projName, assetId, shift, fullDetails, getFloorId(request));
		//request.setAttribute("SRCH_RSLTS", list);
		//request.getSession(true).setAttribute("RESULTS", list);
		view.addObject("showDetails", fullDetails);
		view.addObject("associateList", list);
		view.addObject("cubeId", cubeId);
		view.addObject("source", source);
		
		

		return view;
	}
	
	/**
	 * User session data
	 * @param exportData
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/ats/exportResults1.ats")
	public void exportResults(@RequestParam(value="data", required=false) String exportData, 
			HttpServletRequest request, HttpServletResponse response) {
		
		System.err.println("Export Data : \n\n" + exportData);
		List<AssociateInfoMin> result = (List<AssociateInfoMin>) request.getSession(true).getAttribute("RESULTS");
		ExcelExporter excelExporter = new ExcelExporter();
		byte[] exportbytes = excelExporter.export(result);
		ServletOutputStream outputStream;
		response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=SearchResults.xls"); 
		try {
			outputStream = response.getOutputStream();
			IOUtils.write(exportbytes, outputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping("/ats/exportResults.ats")
	public void exportStaticResults(@RequestParam(value="data", required=false) String exportData, 
			HttpServletRequest request, HttpServletResponse response) {
		
		//System.err.println("Export Data : \n\n" + exportData);
		
		ExcelUIExporter excelExporter = new ExcelUIExporter();
		byte[] exportbytes = excelExporter.exportData(exportData);
		ServletOutputStream outputStream;
		response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=SearchResults.xls"); 
		try {
			outputStream = response.getOutputStream();
			IOUtils.write(exportbytes, outputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	@RequestMapping("/ats/empsInCube.ats")
	public void getEmployyesInCube(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String cubeId = request.getParameter("cubeId");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		
		if(!StringUtils.isEmpty(cubeId)) {
			List<String> employeesInCube = sevice.getEmployeesInCube(cubeId);
			for (int i = 0; i < employeesInCube.size(); i++) {
				writer.print(employeesInCube.get(i));
				if(i != (employeesInCube.size() -1)) {
					writer.print(", ");
				}
			}
		}
		writer.flush();
		writer.close();
	}
	
	@RequestMapping("/ats/add.ats")
	public ModelAndView addEmployeeInCube(@RequestParam(value="cubeId", required=false) String cubeId, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{

		AssociateInfo info = new AssociateInfo();
		NewOdcVO floorById = sevice.getFloorById(getFloorId(request));
		info.setBuilding(floorById.getEstablishmentName());
		info.setWing(floorById.getWing());
		info.setFloor(String.valueOf(floorById.getFloorNumber()));
		ModelAndView view = new ModelAndView("addUpdate");
		info.setSeatNo(cubeId);
		view.addObject("associate", info);
		view.addObject("action", "add");
		view.addObject("shiftsList", getShiftsList());

		return view;
	}
	
	@RequestMapping("/ats/processAdd.ats")
	public String processAdd(@ModelAttribute("associate") AssociateInfo info, 
			BindingResult result,HttpServletRequest request, 
			HttpServletResponse response, Model model){
		aValidator.validate(info, result);
		if(result.hasErrors()) {
			//ModelAndView modelAndView = new ModelAndView("addUpdate");
			//model.addAttribute("associate", info);
			model.addAttribute("action", "add");
			
			model.addAttribute("shiftsList", getShiftsList());
			return "addUpdate";
		}
		sevice.addAssociate(info, getFloorId(request));
		
		model.addAttribute("message", "Associated added to Cube \"" + info.getSeatNo() + "\"");
		return "addUpdateSuccess";
	}
	
	@RequestMapping("/ats/processDelete.ats")
	public String removeAssociate(@ModelAttribute AssociateInfo info, 
			HttpServletRequest request, HttpServletResponse response, Model model){
		
		sevice.removeAssociate(info.getSlNo());
		
		model.addAttribute("message", "Associated deallocated from Cube \"" + info.getSeatNo() + "\"");
		return "addUpdateSuccess";
	}
	
	@RequestMapping("/ats/update.ats")
	public ModelAndView showUpdate(
			@RequestParam(value="recordId", required=true) int recordId,
			@RequestParam(value="action", required=true) String action,
			HttpServletRequest request, 
			HttpServletResponse response) {

		AssociateInfo associateInfo = sevice.getAssociate(recordId, getFloorId(request));
		ModelAndView modelAndView = new ModelAndView("addUpdate");
		modelAndView.addObject("associate", associateInfo);
		modelAndView.addObject("action", "update");
		
		modelAndView.addObject("shiftsList", getShiftsList());
		return modelAndView;
	}

	private Map<String, String> getShiftsList() {
		Map<String,String> hashMap = new TreeMap<String, String>();
		hashMap.put("A", "A - Morning");
		hashMap.put("B", "B - Afternoon");
		hashMap.put("C", "C - Night");
		hashMap.put("D", "D - General");
		return hashMap;
	}
	
	
	@RequestMapping("/ats/processUpdate.ats")
	public String processUpdate(@ModelAttribute("associate") AssociateInfo info, BindingResult result,
			HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		aValidator.validate(info, result);
		if(result.hasErrors()) {
			//ModelAndView modelAndView = new ModelAndView("addUpdate");
			//model.addAttribute("associate", info);
			model.addAttribute("action", "update");
			
			model.addAttribute("shiftsList", getShiftsList());
			return "addUpdate";
		}
		sevice.updateAssociate(info);
		model.addAttribute("message", "Cube \"" + info.getSeatNo() +"\" updated successfully");
		return "addUpdateSuccess";
	}
	
	@RequestMapping("/ats/configureNewFloor.ats")
	public ModelAndView showConfigureNewFloor(@ModelAttribute("odcVo") NewOdcVO odcVO,
			BindingResult result,
			@RequestParam(value="step", required=true) int step,
			HttpServletRequest request, HttpServletResponse response) {

		if(null != request.getSession()) {
			request.getSession().invalidate();
		}
		ModelAndView modelAndView = new ModelAndView("configureFloor");
		modelAndView.addObject("step", step);
		if(1 == step) {
			modelAndView.addObject("odcVo", new NewOdcVO());
		} else if(2 == step) {
//			Collections.
			Map<String, String> availableFloors = sevice.getAvailableFloors();
			validator.setAvailableFloors(availableFloors);
			validator.validate(odcVO, result);
			if(result.hasErrors()) {
				modelAndView = new ModelAndView("configureFloor");
				modelAndView.addObject("step", 1);
			} else {
				odcVO.initialise();
			}
			modelAndView.addObject("odcVo", odcVO);
		} else if(3 == step) {
			sevice.configureFloor(odcVO);
			Map<String, String> floors = sevice.getAvailableFloors();
			modelAndView = new ModelAndView("chooseFloor");
			modelAndView.addObject("alldata", floors);
			modelAndView.addObject("map", new ODC());
		}
		return modelAndView;
//		throw new AtsException("Page Not Implemented", null, new UnsupportedOperationException("Not implemented"));
	}
	
	@RequestMapping("/ats/removeFloor.ats")
	public String removeFloor(@ModelAttribute NewOdcVO odcVO,
			HttpServletRequest request, HttpServletResponse response) {
		String odcId = odcVO.getOdcId();
		
		sevice.removeFloor(odcId);
		HttpSession session = request.getSession();
		if(null != session) session.invalidate();
		return "redirect:/ats/chooseFloor.ats?message=Floor removed successfully";
	}
	
	@RequestMapping("ats/getLogo.ats")
	public void getLogo(HttpServletResponse resp) {
		byte[] logoBytes = sevice.getConfiguredLogo();
		resp.setContentType("image/jpeg");
		ServletOutputStream stream = null;
		try {
			IOUtils.write(logoBytes, stream = resp.getOutputStream());
			resp.flushBuffer();
		} catch (IOException e) {
			throw new AtsException("Error writing image to http response", e);
		} finally {
			IOUtils.closeQuietly(stream);
		}
		
	}
	
	
	/*@RequestMapping("/ats/searchForm.ats")
	public ModelAndView showSearch(@ModelAttribute NewOdcVO odcVO,
			HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("searchForm");
	}*/
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex, HttpServletRequest request){
		
		AtsException aex = null;
		if(ex instanceof AtsException) {
			aex = (AtsException) ex;
		} else {
			aex = new AtsException("An unexpected error occured", ex.getMessage(), ex);
		}
		ModelAndView view = new ModelAndView("error");
		request.setAttribute("ERROR", aex);
		
		return view;
	}
	
	
}
