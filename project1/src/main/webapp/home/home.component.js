$(document).ready(function() {
  $("#dtBasicExampleReimburse tbody tr").click(function() {
    if ($(this).attr("class") == "selected") {
      $(this).removeClass("selected");
    } else {
      $(this)
        .addClass("selected")
        .siblings()
        .removeClass("selected");
    }

    enableReimbursementBtn();
  });

  $("#dtBasicExampleMessage tbody tr").click(function() {
    if ($(this).attr("class") == "selected") {
      $(this).removeClass("selected");
    } else {
      $(this)
        .addClass("selected")
        .siblings()
        .removeClass("selected");
    }

    enableReimbursementBtn();
  });
});

function enableReimbursementBtn() {
  if (document.getElementsByClassName("selected").length != 0) {
    // exists
    document.getElementById("reimburse-del-btn").disabled = false;
  } else {
    document.getElementById("reimburse-del-btn").disabled = true;
  }
}

function deleteMyRecord() {
  let row = document.getElementsByClassName("selected")[0]
  row.parentNode.removeChild(row);
  deleteRecord("reimbursementID="+row.cells[0].innerHTML)
  
}

/* my functions */

function getAllMessagesAndReimbursements() {
  getAllMessages();
  getAllReimbursements();
}

function DeleteRows(table_id) {
  // delete table rows except the header
  let rowCount = table_id.rows.length;
  for (let i = rowCount - 1; i > 0; i--) {
    table_id.deleteRow(i);
  }
}

function insertRequiredField() {
  if (document.getElementById("rejected").selected) {
    document.getElementById("formSalaryEdit").required = true;
    console.log("required true");
  } else {
    document.getElementById("formSalaryEdit").required = false;
    console.log("required false");
  }
}

/* --- ajax  --- */

class Message {
  constructor(msgID, toWho, fromWho, msgContent, dateCreated) {
    this.toWho = toWho;
    this.msgID = msgID;
    this.fromWho = fromWho;
    this.msgContent = msgContent;
    this.dateCreated = dateCreated; // receivedDate
  }
}

//let testMsg = new Message("testToWho", 0, "testFromWHom", "testMsg", "10/10/1000");

function displayMessages(messages) {
  let table = document.getElementById("dtBasicExampleMessage");
  //let row = table.rows[0]
  //let numberOfColumns = row.cells.length;

  DeleteRows(tBasicExampleMessage);

  for (message of messages) {
    let newRow = table.insertRow(1); // inserting a new row to the table as the first row

    newRow.insertCell(0).innerHTML = message.messageId;
    newRow.insertCell(1).innerHTML = message.originEmail;
    newRow.insertCell(2).innerHTML = message.content;
    newRow.insertCell(3).innerHTML =
      message.dateCreated.month +
      "/" +
      message.dateCreated.dayOfMonth +
      "/" +
      message.dateCreated.year;
  }
}

function getAllMessages() {
  let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        displayMessages(JSON.parse(xhr.responseText));
      } else {
        window.alert("Failed to retireve message :(");
      }
    } else {
      window.alert("Fetching Request");
    }
  };
  xhr.open("GET", "message", true);
  xhr.send();
}

/* reimbursement methods below*/

class Reimbursement {
  constructor(
    reimbursementId,
    startDate,
    startTime,
    location,
    description,
    cost,
    gradingFormat,
    eventType,
    justification,
    dateSubmitted,
    email,
    timeMissed
  ) {
    this.reimbursementId = reimbursementId;
    this.startDate = startDate;
    this.startTime = startTime;
    this.location = location;
    this.description = description;
    this.cost = cost;
    this.gradingFormat = gradingFormat;
    this.eventType = eventType;
    this.justification = justification;
    this.dateSubmitted = dateSubmitted;
    this.email = email;
    this.timeMissed = timeMissed;
  }
}

function displayReimbursements(reimbursements) {
  let table = document.getElementById("dtBasicExampleReimburse");
  //let row = table.rows[0]
  //let numberOfColumns = row.cells.length;

  DeleteRows(dtBasicExampleReimburse);

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

function getAllReimbursements() {
  let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        displayReimbursements(JSON.parse(xhr.responseText));
      } else {
        window.alert("Failed to retireve reimbursements :(");
      }
    } else {
      window.alert("Fetching Request");
    }
  };
  xhr.open("GET", "reimbursement", true);
  xhr.send();
}

function deleteRecord(msg) {
  let xhr = new XMLHttpRequest();

  xhr.open("DELETE", "reimbursement", true);
  xhr.send(msg);
}
