# JukeboxSettings API

GET JUKEBOXES THAT SUPPORT GIVEN SETTING ID

GET https://jukebox-settings-app.herokuapp.com/jukebox? id={id}  & model={model}  & offset={offset} & limit={limit}<br>
<pre>id = id of setting you wish to check<br>
model = filter for the model of the jukeboxes (optional)<br>
offset = filter for the offset of the paginated list (optional)<br>
limit = filter for the limit of results (optional)
</pre>
Error codes and messages are displayed when error is encountered, but for reference:<br>
<pre>200 - Everything went well.<br>
404 - When either a model or setting can not be found in the system.<br>
406 - When an input is invalid for any reason.
  
