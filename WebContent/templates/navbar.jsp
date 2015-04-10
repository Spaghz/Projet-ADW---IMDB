<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<ui:composition>
			<div id="navbar" class="navbar-collapse collapse">
				<h:form  class="navbar-form navbar-right">
					<div class="form-group">
						<h:inputText id="inputEmailOrUsername" class="form-control" value="#{beanLogin.emailOrUsername}">
						</h:inputText>
					</div>
					<div class="form-group">
						<h:inputSecret id="passwordInput" class="form-control" value="#{beanLogin.password}"></h:inputSecret>
					</div>
						<h:commandButton type="submit" class="btn btn-success" value="Sign in" style="margin-left:3px;" action="#{beanLogin.login}"></h:commandButton>
				</h:form>
			</div>
</ui:composition>
</html>