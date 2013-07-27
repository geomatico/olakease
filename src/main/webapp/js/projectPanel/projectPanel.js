geomatico.projectPanel = function() {
   var divClass = 'project-panel';
   return {
      init : function() {
         var div = $("<div>").addClass(divClass);
         $('body').append($('<div>').html("PROJECTS"));
         $(document).bind(
            'projects-received',
            function(event, projects) {
               div.empty();
               $.each(projects, function(index, project) {
                  geomatico.projectLine().init(div, project);
               });
               var newProjectDiv = $("<div>").addClass(
                  "project-line").html("New project...");
               newProjectDiv.click(function() {
                  $(document).trigger("project-new");
               });
               div.append(newProjectDiv);
            });
         $('body').append(div);
      }
   };
};