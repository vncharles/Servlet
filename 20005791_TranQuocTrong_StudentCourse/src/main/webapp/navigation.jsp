<!DOCTYPE html>
<html>
<head>
    <title>Navigation</title>
    <style>
        .nav {
            display: flex;
            list-style-type: none;
            background-color: #333;
            padding: 0;
        }
        
        .nav li {
            flex: 1;
            text-align: center;
            padding: 10px;
        }

        .nav li a {
            text-decoration: none;
            color: white;
            font-weight: bold;
        }

        .nav li a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <ul class="nav">
        <li><a href="CourseServlet">Manage Course</a></li>
        <li><a href="StudentServlet">Manage Student</a></li>
    </ul>
</body>
</html>