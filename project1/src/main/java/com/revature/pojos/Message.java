package com.revature.pojos;

import java.time.LocalDate;

public class Message {
	
	private int messageId;
	
	private String originEmail;
	
	private String targetEmail;
	
	private Status status;
	
	private String content;
	
	private LocalDate dateCreated;
	
	
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Message(int messageId, String originEmail, String targetEmail, Status status, String content,
			LocalDate dateCreated) {
		super();
		this.messageId = messageId;
		this.originEmail = originEmail;
		this.targetEmail = targetEmail;
		this.status = status;
		this.content = content;
		this.dateCreated = dateCreated;
	}



	public int getMessageId() {
		return messageId;
	}



	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}



	public String getOriginEmail() {
		return originEmail;
	}



	public void setOriginEmail(String originEmail) {
		this.originEmail = originEmail;
	}



	public String getTargetEmail() {
		return targetEmail;
	}



	public void setTargetEmail(String targetEmail) {
		this.targetEmail = targetEmail;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public LocalDate getDateCreated() {
		return dateCreated;
	}



	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + messageId;
		result = prime * result + ((originEmail == null) ? 0 : originEmail.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((targetEmail == null) ? 0 : targetEmail.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (messageId != other.messageId)
			return false;
		if (originEmail == null) {
			if (other.originEmail != null)
				return false;
		} else if (!originEmail.equals(other.originEmail))
			return false;
		if (status != other.status)
			return false;
		if (targetEmail == null) {
			if (other.targetEmail != null)
				return false;
		} else if (!targetEmail.equals(other.targetEmail))
			return false;
		return true;
	}
	
	


	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", originEmail=" + originEmail + ", targetEmail=" + targetEmail
				+ ", status=" + status + ", content=" + content + ", dateCreated=" + dateCreated + "]";
	}





	public static enum Status{
		SENT, RECEIVED, READ, ALERT
	}

}
