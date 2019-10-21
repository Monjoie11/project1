function showProjectedReimbursementAmt() {
  let cost = document.getElementById("cost").value;
  let eventType = document.getElementById("event-type").value;
  let reimburseAmt = 0;

  switch (eventType) {
    case "University Course":
      reimburseAmt = cost * 0.8;
      break;
    case "Seminar":
      reimburseAmt = cost * 0.6;
      break;
    case "Certification Preparation Class":
      reimburseAmt = cost * 0.75;
      break;
    case "Certification":
      reimburseAmt = cost * 1;
      break;
    case "Technical Training":
      reimburseAmt = cost * 0.9;
      break;
    case "Other":
      reimburseAmt = cost * 0.3;
      break;
    default:
        alert("Error: invalid event type...");
      break;
  }

  reimburseAmt = reimburseAmt.toFixed(2);
  document.getElementById("reimburse-amt").innerHTML = "$" + reimburseAmt;
}
