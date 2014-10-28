fts
===
Floor tracking system a.k.a fts is an application that is used to track the setaing arrangements of employees sitting on a floor.

Checking out and using the project
===
You need to have gradle installed to run the project
You need to have eclipse with git support or this can be downloaded with git command line.
<ol>
<li>Checkout the pproject to your local directory.
<li>Navigate to floor-tracking-system and build it using gradle. Alternatively you can directly deploy to the inbuilt jetty with the jettyRun command.<br>
<code>gradle jettyRun</code>
<li>once deployed, the home page can be accessed using the folowing url
<div>
<code>http://localhost:8000/floor-tracking-system/ats/chooseFloor.ats</code>
</div>
PS: The default build file runs jeety in the 8000 port. You can change it though.
</ol>

After it is up and running, you can find more information at the top right <code>help</code> button on how to use it.
