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

  /* used functions */

  function getAllReimbursementsAdmin() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4) {
        if (xhr.status === 200) {
          displayReimbursementsAdmin(JSON.parse(xhr.responseText));
        } else {
      //    window.alert("Failed to retireve reimbursements :(");
        }
      } else {
       // window.alert("Fetching Request");
      }
    };
    xhr.open("GET", "reimbursement", true);
    xhr.send();
  }

  function displayReimbursementsAdmin(reimbursements) {
    let table = document.getElementById("dtBasicExampleAdmin");
    //let row = table.rows[0]
    //let numberOfColumns = row.cells.length;
  
    DeleteRows(dtBasicExampleAdmin);
  
    for (reimbursement of reimbursements) {
      let newRow = table.insertRow(1); // inserting a new row to the table as the first row
  
      newRow.insertCell(0).innerHTML = reimbursement.reimbursementId;
      newRow.insertCell(1).innerHTML = reimbursement.eventType;
      newRow.insertCell(2).innerHTML = reimbursement.status;
      newRow.insertCell(3).innerHTML = reimbursement.totalAmount; //reimbursed amt calculated based on event type and cost
      newRow.insertCell(4).innerHTML =
        reimbursement.dateSubmitted.month +
        "/" +
        reimbursement.dateSubmitted.dayOfMonth +
        "/" +
        reimbursement.dateSubmitted.year;
    }
  }

  function DeleteRows(table_id) {
    // delete table rows except the header
    let rowCount = table_id.rows.length;
    for (let i = rowCount - 1; i > 0; i--) {
      table_id.deleteRow(i);
    }
  }