
// TODO Написать функцию которая загружает новые сообщения когда
// TODO в чате окно прокручивается до определённого места
// TODO Её нужно поставить на событие scroll.

$(function(){
	init();
});

function init() {
    loadUsersOnline();
    loadMessages();
	$('.formChat').submit(sendMessage);
}

function appendBlockMessage(listMessages) {
	var messagesBlock = $(".messages");

	$.each(listMessages, function(key, value) {
		var divMessage = $('<div>').addClass('message');
		var spanDate = $('<span>').addClass('message-time').text(value.messageDate);
		var spanUsername = $('<span>').addClass('message-username').text(value.username);
		var spanTextMessage = $('<span>').addClass('message-text').text(value.text);

		divMessage.append(spanDate);
		divMessage.append(spanUsername);
		divMessage.append(spanTextMessage);

		messagesBlock.prepend(divMessage);
	});
}

function appendBlockUsers(listUsers) {
	var usersBlock = $(".people");

	$.each(listUsers, function(key, value) {
		var liUsers = $('<li>').text(value.username);

		usersBlock.append(liUsers);
	});
}

// При нажатии на username, функция отправляет ajax запрос на получение данных
// о пользователе
function showUserData() {

}

function loadMessages(messageDate) {
//    var csrfToken = $("meta[name='_csrf']").attr("content");

    $.ajax({
        url: "/messages",
        type: "GET",
        dataType: 'json',
        data: {'messageDate': messageDate},
//        headers: {'X-XSRF-TOKEN':csrfToken},
        success: function(result, status, xhr) {
            appendBlockMessage(result);
        },
        error: function(jqXhr, textStatus, errorMessage) {
            console.log('Error: ' + errorMessage);
        }
    });
}

function loadUsersOnline() {
    $.ajax({
            url: "/users",
            type: "GET",
            dataType: 'json',
    //        headers: {'X-XSRF-TOKEN':csrfToken},
            success: function(result, status, xhr) {
                appendBlockUsers(result);
            },
            error: function(jqXhr, textStatus, errorMessage) {
                console.log('Error: ' + errorMessage);
            }
        });
}

function createMessage(data) {
    return new Message(data.messageDate, data.username, data.text)
}


function sendMessage() {
    var messageDate = $('.message-time:first').text();
	var textMessage = $('.message-input-field').val();
	var username = $('.you-name').text();
	var message = new Message(textMessage, username, messageDate);
	var messages = JSON.stringify(message);
//	var csrfToken = $("meta[name='_csrf']").attr("content");
//	var csrfToken = $.cookie('XSRF-TOKEN');
//    var csrfCookie = document.cookie;
//    var csrfToken = csrfCookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
//    headers: { 'X-XSRF-TOKEN': csrfToken },

	$.ajax({
		url: "/messages",
		type: "POST",
		data: messages,
		contentType: 'application/json',
//		headers: {'X-XSRF-TOKEN':csrfToken},
		success: function(data, status, xhr) {
		    $('.formChat').trigger("reset");
		    appendBlockMessage(data);
		    loadMessages($('.message-time:first').text());
		},
		error: function(jqXhr, textStatus, errorMessage) {
		    console.log('Error: ' + errorMessage);
		}
	});
	return false;
}

//function logout() {
//    var csrfToken = $("meta[name='_csrf']").attr("content");
//    var username = $('#username').text();
//
//    $.ajax({
//        url: "/logout",
//        type: "POST",
//        contentType: 'application/json',
//        data: {'username': username},
//        headers: {'X-XSRF-TOKEN':csrfToken},
//        success: function(data, status, xhr) {
//            console.log(username + " Logout.");
////            appendBlockMessage(result);
//        },
//        error: function(jqXhr, textStatus, errorMessage) {
//            console.log('Error: ' + errorMessage);
//        }
//    });
//    return false;
//}



