function addRowHandlersToAdmin() {
    let table = document.getElementById("dtBasicExampleAdmin");
    let rows = table.getElementsByTagName("tr");
    for (i = 0; i < rows.length; i++) {
      let currentRow = table.rows[i];
      let createClickHandler = function(row) {
        return function() {
          doYourThingForAdmin(row);
        };
      };
      currentRow.onclick = createClickHandler(currentRow);
    }
  }
  
  function doYourThingForAdmin(row) {
    if (row.className == "selectedAdmin") {
      row.className = "";
    } else {
      let currentSelected = document.getElementsByClassName("selectedAdmin");
      for (let i = 0; i < currentSelected.length; i++) {
        currentSelected[i].className = "";
      }
      row.className = "selectedAdmin";
    }
  }