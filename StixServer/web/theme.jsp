<%-- 
    Document   : theme
    Created on : Feb 5, 2010, 7:47:36 PM
    Author     : alex
--%>
<%
    String zone = "repository";
    if (request.getParameter("zone") != null)
        zone = request.getParameter("zone");

    String part = "top";
    if (request.getParameter("part") != null)
        part = request.getParameter("part");

    String title = "Stix";
    if (request.getParameter("title") != null)
        title += " &mdash; " + request.getParameter("title");

    if (part.equals("bottom")) {
        %>
                                        </div>
				</div>
				<div class="clear"></div>
				<div id="footer">
					<div id="footer-bg">
						<div class="float-left">
							<p>
                                                                <p>The time is <%= new java.util.Date() %></p>
								The STIX project &mdash; WIMO Group &mdash; ICSA &mdash University of Edinburghs<br />
                                                                &copy; 2010 Mino Bernardi, Damon Fenacci, Matt Calder, Alex Macmillan<br />
                                                                <tt>Working Prototype - Absolutely not for Public Release</tt>
							</p>
						</div>
					</div>
				</div>
				<div id="footer-bottom"></div>
				<div id="footer-shadow"></div>
			</div>
			<div id="right-shadow"></div>
			<div class="clear"></div>
		</div>
	</body>
</html>
        <%
    } else {
        %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>
			<%= title %>
		</title>

		<!-- styles -->
		<link href="static/reset.css" rel="stylesheet" type="text/css" media="screen" />
		<link href="static/typography.css" rel="stylesheet" type="text/css" media="screen" />
		<link href="static/style.css" rel="stylesheet" type="text/css" media="screen" />

		<!-- jquery -->
		<script type="text/javascript" src="static/jquery-1.js"></script>

		<!-- tabs -->
		<script src="static/ui_002.js" type="text/javascript"></script>
		<script src="static/ui.js" type="text/javascript"></script>
		<link rel="stylesheet" href="static/ui.css" type="text/css" media="screen" />

		<!-- initialize tabs -->
		<script type="text/javascript">
		//<![CDATA[
			$(document).ready(function(){
				$('#container-1 > ul').tabs();
			});
		//]]>
		</script>

	</head>
	<body>
		<div id="wrapper">
			<div id="left-shadow"></div>
			<div id="page">
				<div id="top">
					<div id="top-items">
						<!-- BEGIN LOGO -->
						<div id="logo">
							<a href="/StixServer/"><img src="/StixServer/static/stixLogo.png" alt="STIX" /></a>
						</div>
						<!-- END OF LOGO -->
					</div>
					<div id="navmenu-container">
						<!-- BEGIN TAB NAVIGATION -->
						<div id="nav-menu2">
							<ul class="menu">
								<li class="first">
                                                                    <a href="/StixServer/topology.jsp" <% if (zone.equals("topology")) { %> class="current" <% } %>><span>Topology</span></a>
								</li>
								<li>
									<a href="/StixServer/" <% if (zone.equals("repository")) { %> class="current" <% } %>><span>Repository</span></a>
								</li>
								<li class="last">
									<a href="/StixServer/perspectives.jsp" <% if (zone.equals("view")) { %> class="current" <% } %>><span>View</span></a>
								</li>
							</ul>
						</div>
						<!-- END OF  TAB NAVIGATION -->
					</div>
				</div>
				<div class="clear"></div>
				<div id="header">
					<div id="header-top"></div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
				<!-- BEGIN SUB-HEADER -->

				<!--

                                <div id="sub-header">
					<div id="sub-header-bg">
						 <div class="sub-header-text">
							<p>
								Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
							</p>
						</div> 
					</div>
				</div>

                                -->

                                <!-- END OF SUB-HEADER -->

				<div id="content">
					<div id="main">
                                            <%
    }
%>
