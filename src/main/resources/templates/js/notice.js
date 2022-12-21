var token = $('#_csrf').attr('content');
var header = $('#_csrf_header').attr('content');

function create(theme) {
    $.ajax({
        url: '/notices/create',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        data: {
            "theme": theme,
            "description": "",
            "positionTop": 0,
            "positionLeft": 0
        },
        type: 'POST',
        success: function () {
            location.reload();
        }
    });
}

function remove(noticeId) {
    $.ajax({
        url: '/notices/remove',
        data: {"id": noticeId },
        type: 'DELETE',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function () {
            location.reload();
        }
    });
}

function update(noticeId) {
    const notice = $('#' + noticeId);
    const description = notice.find("textarea").val();
    const position = notice.offset();
    const theme = notice.attr("theme");
    $.ajax({
        url: '/notices/update',
        type: 'PUT',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        data: {
            "id" : noticeId,
            "theme": theme,
            "description": description,
            "positionTop": position.top,
            "positionLeft": position.left
        },
    });
}

function closeNotice(noticeId) {
    $("#confirm").data("noticeId", noticeId).dialog("open");
}

$(function () {
    $(".notice").draggable();
    $("#confirm").dialog({
        resizable: false,
        height: "auto",
        width: 400,
        autoOpen: false,
        modal: true,
        buttons: {
            "Yes, I am sure.": function() {
                var noticeId = $( this ).data("noticeId");
                remove(noticeId);
                $( this ).dialog( "close" );
            },
            Cancel: function() {
                $( this ).dialog( "close" );
            }
        }
    });
});