<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MY BLOG</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
<link rel="stylesheet" href="/resources/css/common.css?ver=1">
<script defer src="/resources/js/common.js?ver=8"></script>
</head>
<body id="wrap">
	<div id="header"><tiles:insertAttribute name="header"/></div>
    <div id="body"><tiles:insertAttribute name="body"/></div>
</body>
</html>