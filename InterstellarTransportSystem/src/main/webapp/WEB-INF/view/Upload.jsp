<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body style='background-color: #0066FF;'>

<h1>Upload your Galaxy Information</h1>

<form method="POST" action="/TransportSystem/RouteUpload/ExcelUpload" enctype="multipart/form-data">
    <input type="file" name="file" /><br/><br/>
    <input type="submit" value="Submit" />
</form>

</body>
</html>