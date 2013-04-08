<html>
<body>
	<h1>Create a Survey</h1>
	<form action="create" method="post">
	<div>
	<label for="surveyName">Name:</label>
	<input type="text" name="surveyName" id="surveyName"/>
	</div>

	<div>
	 <fieldset>
      <legend>Survey Options(No more than 5 options):</legend>
      Option1: <input type="text" name="option1"><br>
      Option2: <input type="text" name ="option2"><br>
      Option3: <input type="text" name ="option3"><br>
      Option4: <input type="text" name ="option4"><br>
      Option5: <input type="text" name ="option5"><br>
     </fieldset>
<div>
<label for="surveyName">Category:</label>
<select name="category">
  <option value="Science">Science</option>
  <option value="Economy">Economy</option>
  <option value="Culture">Culture</option>
  <option value="Art">Art</option>
</select>
</div>

<div>
<label for="isMultiple">Is multiple:</label>
<input checked="checked" name="isMultiple" type="radio" value="true" /> Yes
<input name="isMultiple" type="radio" value="false" /> No
	</div>

		<div>
    	<label for="tag">Tags(Seperated by (,)):</label>
    	<input type="text" name="tags" id="tags"/>
    	</div>

    	<div>
        <input id="submitButton" name="submitButton" type="submit"/>
    	</div>
	</form>
</body>
</html>