<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
 <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="<c:url value="/resource/"/>dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Alexander Pierce</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->

		<c:import var="menu" url="/resource/config/menu.xml" charEncoding="UTF-8"/>
		<x:parse xml="${menu}" var="output" />
		<ul class="sidebar-menu">
			<li class="header"><x:out select="$output/menu/nodes/header" /></li>
			<x:forEach select="$output/menu/nodes/node" var="item">
				<li class="treeview">
					<a href="<x:out select="$item/url" />">
					<i class="fa <x:out select="$item/icon" />"></i> <span><x:out select="$item/name" /></span> 
					<x:if select="$item/children">
						<span class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
						</span>
					</x:if>
					</a> 
					<x:if select="$item/children">
						<ul class="treeview-menu">
							<x:forEach select="$item/children/node" var="citem">
								<li><a href="<x:out select="$citem/url" />">
								<i class="fa fa-circle-o"></i>
								<x:out select="$citem/name" /></a>
								</li>
							</x:forEach>
						</ul>
					</x:if>
				</li>
			</x:forEach>

		</ul>
	</section>
    <!-- /.sidebar -->
  </aside>