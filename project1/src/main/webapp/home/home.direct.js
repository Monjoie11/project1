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

  function getAllMessages() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4) {
        if (xhr.status === 200) {
          displayMessages(JSON.parse(xhr.responseText));
        } else {
    //      window.alert("Failed to retireve message :(");
        }
      } else {
    //    window.alert("Fetching Request");
      }
    };
    xhr.open("GET", "message", true);
    xhr.send();
  }