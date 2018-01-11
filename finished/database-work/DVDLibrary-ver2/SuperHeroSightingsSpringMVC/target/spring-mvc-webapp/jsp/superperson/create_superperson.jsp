<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Super Person</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-2">
                    <h1>Register Super Person</h1>
                </div>
            </div>
            <hr/>


            <div class="row">

                <!-- FORM -->

                <sf:form id="createSuperPersonForm" class="form-horizontal" commandName="cspcm"
                         action="${pageContext.request.contextPath}/superperson/createSuperPerson" method="POST">

                    <!-- NAME -->
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Name </label>
                        <div class="col-xs-5">

                            <input id="name" name="name" type="text" path="name" placeholder="Enter name" value="${cspcm.name}"/>
                            <span><sf:errors path="name" cssclass="error"></sf:errors></span>
                            </div>
                        </div>

                        <!-- POWERS -->

                        <div class="form-group">
                            <label class="col-xs-3 control-label"> Powers </label>
                            <div class="col-xs-5">
                                <select multiple name="powers" form="createSuperPersonForm" path="powers">
                                <c:forEach
                                    items="${spvm.powers}" 
                                    var="currentPower" 
                                    varStatus="loop">
                                    <option value="${currentPower.powerId}"

                                            <c:forEach
                                                items="${cspcm.powers}"
                                                var="currentSelectedPowerId">
                                                <c:if test="${currentPower.powerId == currentSelectedPowerId}">
                                                    selected
                                                </c:if>
                                            </c:forEach>

                                            >${currentPower.name}</option>
                                </c:forEach>
                            </select><span><sf:errors path="powers" cssclass="error"></sf:errors></span>
                            </div>
                        </div>

                        <!-- Organizations -->

                        <div class="form-group">
                            <label class="col-xs-3 control-label"> Organizations </label>
                            <div class="col-xs-5">
                                <select multiple name="organizations" form="createSuperPersonForm" path="organizations">
                                <c:forEach
                                    items="${spvm.organizations}" 
                                    var="currentOrganization">
                                    <option value="${currentOrganization.organizationId}"

                                            <c:forEach
                                                items="${cspcm.organizations}"
                                                var="currentCMOrg"
                                                >
                                                <c:if test="${currentOrganization.organizationId == currentCMOrg}">
                                                    selected
                                                </c:if>
                                            </c:forEach>

                                            >
                                        ${currentOrganization.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>         


                    <!-- DESCRIPTION -->

                    <div class="form-group">
                        <div id="description" name="description" path="description">Add Description</div>
                    </div>

                    <!-- REPUTATION -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Reputation </label>
                        <div class="col-xs-5">
                            <sf:errors path="reputation" cssclass="error"></sf:errors>
                                <div class="input-group">
                                <c:choose>
                                    <c:when test="${not empty cspcm.reputation}">
                                        <c:choose>
                                            <c:when test="${cspcm.reputation eq 'good'}">
                                                <input name="reputation" type="radio" path="reputation" value="good" checked/> Good <br>
                                                <input name="reputation" type="radio" path="reputation"value = "evil"/> Evil <br>
                                            </c:when>
                                            <c:otherwise>
                                                <input name="reputation" type="radio" path="reputation" value="good"/> Good <br>
                                                <input name="reputation" type="radio" path="reputation"value = "evil" checked/> Evil <br>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <input name="reputation" type="radio" path="reputation" value="good"/> Good <br>
                                        <input name="reputation" type="radio" path="reputation"value = "evil"/> Evil <br>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>

                    <!-- BUTTONS -->

                    <div class="form-group">
                        <div class="col-xs-5 col-xs-offset-3">
                            <button type="submit" class="btn btn-default" id="btnCreateSuperPerson">Add Super Person</button>
                            <button type="submit" class="btn btn-default" id="btnCancel" formaction="${pageContext.request.contextPath}/superperson/superpersons" formmethod="GET">Cancel</button>
                        </div>
                    </div>
                </sf:form>

            </div>
        </div>
        <!-- Main Page Content Start -->
        <!-- Main Page Content Stop -->

        <!-- ###Page to show 2 buttons -->
        <div class="col-md-12 main-button-container">
            <div class="well center-block first-upload-container">
                <button type="button" class="btn btn-success btn-lg btn-block" id="upload-new">
                    Upload New Media
                </button>
                <button type="button" class="btn btn-info btn-lg btn-block" id="select-media">
                    Select Media
                </button>
            </div>
        </div>
        <input type="hidden" name="media-url" id="media-url" data-media-type="image" data-caption=""/>

        <!-- ###Page to upload new media -->

        <div class="col-md-12 upload-new-container">
            <h3>Upload New Media File</h3>
            <p>Media File</p>
            <input type="file" name="mediafile-new" id="mediafile-new" />
            <br/>
            <p>Caption</p>
            <input type="text" name="caption-new" id="caption-new" class="form-control"/>
            <br/>
            <button class="btn btn-block btn-success" id="upload-new-media">Upload</button>
            <button class="btn btn-block btn-error" id="upload-new-cancel">Cancel</button>
        </div>

        <!-- ###Page to select old media -->

        <div class="col-md-12 select-media-viewer well center-block">
            <h3 class="media-viewer-title">Media On File</h3>
            <p class="media-viewer-subtext">
                Please click the media you want to add to your article. Then Click use media</p>
            <br/>
            <div class="gallery">

            </div>
            <button class="btn btn-block btn-info use-media"> Use Media</button>
            <button class="btn btn-block btn-error" id="use-media-cancel">Cancel</button>
        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/tinymce_4.7.2/tinymce/plugins/imageuploader/plugin.min.js"></script>
        //<script src="https://cloud.tinymce.com/stable/tinymce.min.js"></script>
        
        <script type="text/javascript">
            tinymce.init({
                selector: '#description',
                theme: 'modern',
                width: 600,
                height: 300,
                plugins: [
                    'advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker',
                    'searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking',
                    'save table contextmenu directionality emoticons template paste textcolor',
                    'preview fullscreen'
                ],
                content_css: 'css/content.css',
                toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullpage | forecolor backcolor emoticons | preview | fullscreen',
                inline: true

            });
            var data = new FormData();
data.append('filename', $('#mediafile-new').prop('files')[0]);
$.ajax({
    type: 'POST',
    processData: false, // important
    contentType: false, // important
    data: data,
    url: '/home/matt/Pictures',
    dataType : 'text',
    success: function(jsonData){
     console.log("Great job");
    },
    error: function(){
        console.log("Shit job");
    }
});
        </script>
        
    </body>
</html>

<!-- 

DESCRIPTION
original version -- working


                    <div class="form-group">
                        <label class="col-xs-3 control-label">Description</label>
                        <div class="col-xs-5">
                            /*<sf:errors path="description" cssclass="error"></sf:errors>*/
                            <textarea name="description" id="description" class="form-control" rows="5" path="description" placeholder="Enter description">${cspcm.description}</textarea>
                        </div>
                    </div>

-->