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

    enableMsgBtn();
  });
});

function DisplaySelectedReimbursementDetails(reimburseObj) {
  // document.getElementById("namehere").innerHTML =
  // document.getElementById("departhere").innerHTML =
  // document.getElementById("cemailhere").innerHTML =
  // document.getElementById("phonehere").innerHTML =
  // document.getElementById("rolehere").innerHTML =

  document.getElementById("reimburseidhere").innerHTML =
    reimburseObj.reimbursementId;
  document.getElementById("reqdatehere").innerHTML =
    reimburseObj.startDate.month +
    "/" +
    reimburseObj.startDate.dayOfMonth +
    "/" +
    reimburseObj.startDate.year;

  document.getElementById("reqtimehere").innerHTML =
    reimburseObj.startTime.hour + ":" + reimburseObj.startTime.minute;

  document.getElementById("lochere").innerHTML = reimburseObj.location;
  document.getElementById("deschere").innerHTML = reimburseObj.description;
  document.getElementById("costhere").innerHTML = reimburseObj.cost;
  document.getElementById("gradinghere").innerHTML = reimburseObj.gradingFormat;
  document.getElementById("justhere").innerHTML = reimburseObj.justification;
  document.getElementById("sdatehere").innerHTML =
    reimburseObj.dateSubmitted.month +
    "/" +
    reimburseObj.dateSubmitted.dayOfMonth +
    "/" +
    reimburseObj.dateSubmitted.year;
  document.getElementById("eventhere").innerHTML = reimburseObj.eventType;
  document.getElementById("missedhere").innerHTML = reimburseObj.timeMissed;
  document.getElementById("statushere").innerHTML = reimburseObj.status;
  document.getElementById(
    "projamthere"
  ).innerHTML = showProjectedReimbursementAmt(
    reimburseObj.cost,
    reimburseObj.eventType
  );
}

function showProjectedReimbursementAmt(cost, eventType) {
  let reimburseAmt = 0;

  switch (eventType) {
    case "University Course":
      reimburseAmt = parseInt(cost) * 0.8;
      break;
    case "Seminar":
      reimburseAmt = parseInt(cost) * 0.6;
      break;
    case "Certification Preparation Class":
      reimburseAmt = parseInt(cost) * 0.75;
      break;
    case "Certification":
      reimburseAmt = parseInt(cost) * 1;
      break;
    case "Technical Training":
      reimburseAmt = parseInt(cost) * 0.9;
      break;
    case "Other":
      reimburseAmt = parseInt(cost) * 0.3;
      break;
    default:
      alert("Error: invalid event type...");
      break;
  }

  reimburseAmt = reimburseAmt.toFixed(2);
  reimburseAmt = "$" + reimburseAmt;
  return reimburseAmt;
}

function getReimbursementByID() {
  let xhr = new XMLHttpRequest();
  let row = document.getElementsByClassName("selected")[0];
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        DisplaySelectedReimbursementDetails(JSON.parse(xhr.responseText));
      } else {
        window.alert("Failed to retireve reimbursement :(");
      }
    } else {
      window.alert("Fetching Request");
    }
  };
  xhr.open("GET", "reimbursement", true);
  xhr.send("reimbursementID=" + row.cells[0].innerHTML);
}

function enableMsgBtn() {
  if (document.getElementsByClassName("selected").length != 0) {
    // exists
    document.getElementById("msg-ops-btn-del").disabled = false;
  } else {
    document.getElementById("msg-ops-btn-del").disabled = true;
  }
}

function enableReimbursementBtn() {
  if (document.getElementsByClassName("selected").length != 0) {
    // exists
    document.getElementById("reimburse-del-btn").disabled = false;
  } else {
    document.getElementById("reimburse-del-btn").disabled = true;
  }
}

function deleteMyRecord() {
  // for reimbursement
  let row = document.getElementsByClassName("selected")[0];
  row.parentNode.removeChild(row);
  deleteRecord("reimbursementID=" + row.cells[0].innerHTML);
}

function deleteMyMsg() {
  let row = document.getElementsByClassName("selected")[0];
  row.parentNode.removeChild(row);
  deleteMsg("msgID=" + row.cells[0].innerHTML);
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

  DeleteRows(dtBasicExampleMessage);

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

function deleteRecord(reimburse) {
  let xhr = new XMLHttpRequest();

  xhr.open("DELETE", "reimbursement", true);
  xhr.send(reimburse);
}

function deleteMsg(msg) {
  let xhr = new XMLHttpRequest();

  xhr.open("DELETE", "message", true);
  xhr.send(msg);
}