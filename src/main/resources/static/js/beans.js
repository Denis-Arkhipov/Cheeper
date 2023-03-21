function User(username, password) {
	this.username = username;
	this.password = password;
}

function Message(textMessage, username, messageDate) {
    this.textMessage = textMessage;
	this.username = username;
	this.messageDate = messageDate;
}