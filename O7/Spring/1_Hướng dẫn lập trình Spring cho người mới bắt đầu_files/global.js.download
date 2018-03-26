// http://jsbeautifier.org/
var hostname = new RegExp(location.host);


function getEndNumber(str) {
    var idx = str.lastIndexOf('-');
    return parseInt(str.substring(idx + 1));
}

function showCKEDITOR(parentId) {
    $("#" + parentId + "  textarea.editor1").ckeditor(
        CKEDITOR_CONFIG_1
    );
    $("#" + parentId + " textarea.editor0").ckeditor(
        CKEDITOR_CONFIG_0
    );
}

// Kiem tra login.
function isLogined() {
    var result = false;
    $.ajax({
        url: CHECK_LOGINED_URL,
        async: false
    }).
    done(function(data) {
        var div = $(data).filter("#thirdUserId");
        if (div.length > 0) {
            result = true;
        } else {
            result = false;
        }
    }).fail(function(xhr) {
        alert("check login fail " + xhr.responseText);
        return false;
    });
    return result;
}

// --------------------------------------------------------------------------

function configCKEditor() {

    $("textarea#editor0").ckeditor(
        CKEDITOR_CONFIG_0
    );
    $("textarea#editor1").ckeditor(
        CKEDITOR_CONFIG_1
    );
}

// --------------------------------------------------------------------------

function configIssue() {

    // Issue Product Component Change.
    $('body').on("change", "select[name='productComponentId']", function() {
        var option = $(this).find(":selected");
        var desc = option.attr("description");
        if (desc) {
            $("#issue-product-component-desc").html(desc);
        } else {
            $("#issue-product-component-desc").html("&nbsp;");
        }
    });



    // Create Issue Topic : combobox issue product change.
    $("select[name='productId']").on("change", function() {
        var option = $(this).find(":selected");
        var value = option.attr("value");

        $("#issue-product-version-container > select").empty();
        $("#issue-product-component-container > select").empty();
        if (value == "") {
            return;
        }
        var url = option.attr("ajaxUrl");

        $.post(url).done(function(data) {
            $("#issue-product-version-container").html($(data).find("[name='productVersionId']"));
            $("#issue-product-component-container").html($(data).find("[name='productComponentId']"));
        }).fail(function() {
            alert("error");
        })

    });

    $("#form-issue-message").submit(function(event) {

        var content = $(this).find("textarea[name='messageContent']").val();
        if (content.length < 20) {
            alert("Minimum 20 characters");
            return false;
        }
        var isLogin = isLogined();
        if (!isLogin) {
            alert("Please login first");
            window.open(LOGIN_URL);
            return false;
        }
    });

    $("#form-issue-topic").submit(function() {
        if ($("#form-issue-topic [name='productId']").length == 0 || !$("#form-issue-topic [name='productId']").val()) {
            alert("Required");
            return false;
        }
        if ($("#form-issue-topic  [name='productComponentId']").length == 0 || !$("#form-issue-topic  [name='productComponentId']").val()) {
            alert("Required");
            return false;
        }

        if ($("#form-issue-topic input[name='topicTitle']").val().length < 10) {
            alert("Title to short");
            return false;
        }
        if ($("#form-issue-topic textarea[name='topicContent']").val().length < 20) {
            alert("Content minimum 20 characters");
            return false;
        }
        var isLogin = isLogined();
        if (!isLogin) {
            alert("Please login first");
            window.open(LOGIN_URL);
            return false;
        }

    });

    // Save comment!
    $('body').on("submit", "div[id^='comment-form-container-'] > form", function(event) {
        event.preventDefault();
        var isLogin = isLogined();
        if (!isLogin) {
            alert("Please login first");
            window.open(LOGIN_URL);
            return false;
        }

        var $form = $(this);
        var commentContent = $form.find("textarea").val();

        if (!commentContent || commentContent.length < 15) {
            alert("Please type content, min 15 characters");
            return;
        }
        var url = $form.attr("action");
        // Send the data using post

        var postData = $form.serializeArray();
        var posting = $.post(url, postData);
        // Put the results in a div
        posting.done(function(data) {
            var divId = $(data).filter("div[id^='comment-']").attr("id");
            var hiddenDiv = $form.parent().siblings(".comments").find("#" + divId);
            if (hiddenDiv.length != 0) {
                hiddenDiv.replaceWith(data);
            } else
            if (hiddenDiv.length == 0) {
                $form.parent().siblings(".comments").append(data);
            }
            $form.parent().empty();
        });
        posting.fail(function(data) {
            alert("Fail:" + data);
        });
    });

    // ---------------------------------------------------------------------------------------
    // Click add-comment-mc-xxx hoac add-comment-tc-xxx
    $('body').on("click", "a[id^='add-comment-']", function() {
        var id = $(this).attr("id");
        var isTopicComment = id.indexOf("tc") > 0;
        var tmId = getEndNumber(id);
        var suffix = id.substring("add-comment-".length);

        var commentId = $("#comment-form-container-" + suffix + " > form > input[name='commentId']").val();
        //
        if (commentId) {
            return;
        }
        // On new comment form
        if (commentId == "") {
            return;
        }
        var commentUrlId = "add-comment-url-" + suffix;
        var createUrl = $("#" + commentUrlId).val();

        var postData = {};
        if (isTopicComment) {
            postData = {
                "topicId": tmId
            };
        } else {
            postData = {
                "messageId": tmId
            };
        }
        $.post(createUrl, postData)
            .done(function(data) {
                $("#comment-form-container-" + suffix).html(data);
                showCKEDITOR("comment-form-container-" + suffix);
            });
    });
    // ---------------------------------------------------------------------
    // Click edit-comment-xxx
    $('body').on("click", "a[id^='edit-comment-']", function() {
        var id = $(this).attr("id");
        var commentId = getEndNumber(id);
        var editUrl = $("#edit-comment-url-" + commentId).val();
        // alert("Eidt="+editUrl);

        var parentId = $(this).parentsUntil("[id^='comments-']").parent().attr("id");
        // mc-xxxx or tc-xxxx
        var suffix = parentId.substring("comments-".length);


        $.post(editUrl).done(function(data) {
            $("#comment-form-container-" + suffix).html(data);
            showCKEDITOR("comment-form-container-" + suffix);
            $("#" + parentId + " > div[id^='comment-']").show();
            $("#comment-" + commentId).hide();
        });

        // $("#comment-" + commentId).hide();
    });
    // ----------------------------------------------------------------------
    $('body').on("click", "a[id^='delete-comment-']", function() {
        var isLogin = isLogined();
        if (!isLogin) {
            alert("Please login first");
            window.open(LOGIN_URL);
            return false;
        }
        var confirm = window.confirm("Area you sure to delete?");
        if (confirm == false) {
            return;
        }
        var id = $(this).attr("id");
        var commentId = getEndNumber(id);

        var html = $("#comment-" + commentId).html();

        var deleteUrl = $("#delete-comment-url-" + commentId).attr("value");

        var postData = {
            "form-action": "delete",
            "deleteId": commentId
        };

        $.post(deleteUrl, postData).done(function(data) {
            if ("deleted" == data) {
                $("#comment-" + commentId).remove();
            } else {
                alert("delete result:" + data);
            }
        });

    });

}

// --------------------------------------------------------------------------
function __s__() {
   try {
       var originUrl = window.location.href;
       if(originUrl.startsWith("http://") || originUrl.startsWith("https://") ) {
           originUrl = originUrl.replace("://","://s.");
           var i = originUrl.indexOf('//s');
           var j = originUrl.indexOf("/", i+ 3);
           var url = originUrl.substring(0, j) + "/aweb"+originUrl.substring(  j);
          
           $('#aweb').html('<iframe src="'+url+'" frameborder="0" scrolling="no"></iframe>');
       }
   }catch(e){}
}
// --------------------------------------------------------------------------
function configAdsFloatBar() {
    $(window).scroll(function() {
        var markerTop = $('#ads-float-marker').position().top;
        var bottom = $('#footer-container').position().top;
        var windowScrollTop = $(window).scrollTop();
        var delta1 = Math.ceil(markerTop - windowScrollTop);
        var delta2 = Math.ceil(bottom - windowScrollTop);
        var adsFloatHeight = $("#ads-float").height();
        //
        if (delta1 < 0 ) {  
            $('#ads-float').css('position', 'fixed');
            $('#ads-float').css('top', '20px');
            $('#ads-float').css('padding', '5px');
            $('#ads-float').css('height', (delta2-40)+'px');
            $('#ads-float').css('overflow', 'hidden');
        } else {
            $('#ads-float').removeAttr('style');
        }
    });
}
// --------------------------------------------------------------------------
function setLinkIcons() {
    $('a').each(function() {

        var url = $(this).attr("href");
        var target = $(this).attr("target");
        if (url) {
            // Test if current host (domain) is in it
            if (hostname.test(url)) {
                // If it's local...
                // $(this).addClass('internal-url');
            } else if (url.slice(0, 1) == "/") {
                // $(this).addClass('internal-url');
            } else if (url.slice(0, 1) == "#") {
                // It's an anchor link
                $(this).addClass('internal-page-anchor');
                $(this).removeAttr("href");
                $(this).attr("data-href", url);
            } else {
                // a link that does not contain the current host
                // $(this).attr("target", "_blank");
                // $(this).addClass('external-url');
            }
        }
        if (target == "_blank") {
            if ($(this).children().length == 0) {
                $(this).addClass('target-blank-url');
            }
        }
    });
}
// --------------------------------------------------------------------------
function setLinkClick() {
    $("a").click(function() {

        var href = $(this).attr("data-href");

        if (href) {
            location.href = href;
        }
    });
}
// --------------------------------------------------------------------------
function applySlider() {
    $(".owl-carousel").owlCarousel({

        slideSpeed: 300,
        paginationSpeed: 400,
        pagination: true,
        singleItem: true

        // Show next and prev buttons
        // navigation : true,
        // singleItem:true
        // autoPlay:3000,
        // items : 1,
        // itemsDesktop : false,
        // itemsDesktopSmall : false,
        // itemsTablet: false,
        // itemsMobile : false

    });
}

// --------------------------------------------------------------------------
var hideByUser = false;

function setFixedMenuVisible(visible, isUserAction)  {
        var panel = $('#style-switcher');
        var width = $("#style-switcher-panel").width();
        
        if(visible && !panel.hasClass('active') )  {
            panel.addClass('active');
            panel.animate({
                right: 0
            }, width);
            $('#style-switcher-toggle').removeClass("off");
            $('#style-switcher-toggle').addClass("on");    
            if(isUserAction)  {
                hideByUser = false;
            }                
        } else if (!visible && panel.hasClass('active') ) {
             panel.animate({
                right: -width
            }, width);
            panel.removeClass('active');
            $('#style-switcher-toggle').removeClass("on");
            $('#style-switcher-toggle').addClass("off");              
            if(isUserAction)  {
                hideByUser = true;
            }
        }
}
// --------------------------------------------------------------------------


function setOnClickFixedMenu() {
    $("#style-switcher a[name='top']").remove();
    //
    $('#style-switcher-toggle').on('click', function(e) {  
        var panel = $('#style-switcher');
        var visible = !panel.hasClass('active');
        setFixedMenuVisible(visible, true) ;
    });
}
// --------------------------------------------------------------------------


function configShowHideFixedMenuWhenScroll() {  
              
    $(window).scroll(function() {
        var windowScrollTop = $(window).scrollTop();
        if(windowScrollTop < 300 )  {
            setFixedMenuVisible(false, false) ;  
        } else {
            if (!hideByUser ) {
                setFixedMenuVisible(true, false) ;  
            }
        }
    });
}    

// --------------------------------------------------------------------------

function hideEmptyCategory() {
    $("div.cat-entry > ul").each(function() {

        if ($(this).children().length == 0) {
            $(this).parent().hide();
        }
    });
}
// --------------------------------------------------------------------------
$(document).ready(function() {
   // __s__();
    setLinkIcons();
    setLinkClick();
    hideEmptyCategory();
    applySlider();

    setOnClickFixedMenu();
    setFixedMenuVisible(false, false);
     
    configAdsFloatBar();
    configShowHideFixedMenuWhenScroll();
    // configIssue();


});
// --------------------------------------------------------------------------