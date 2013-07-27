geomatico.projectLine = function() {
   var divClass = 'project-line';
   var selectedDivClass = 'selected-project-line';
   return {
      init : function(parent, project) {
         var div = $("<div>").addClass(divClass).html(
            project.name + ": " + project.description);
         div.hover(function() {
            $(document).trigger("project-selected", project);
         });
         $(document).bind("project-selected", function(event, selectedProject) {
            if (project.id == selectedProject.id) {
               div.addClass(selectedDivClass);
            } else {
               div.removeClass(selectedDivClass);
            }
         });
         parent.append(div);
      }
   };
};