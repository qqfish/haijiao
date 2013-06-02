
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function UserMedia(){
    this.pc = null;
    this.video = null;
    this.container = null
    this.userButton = null;
    this.stream = null;
    this.status = null;
}

function Media(parentId, text, userList){
  
    var width = 240;
    var height = 180;
    var userListDiv = $("#" + userList);
    var dragPlace = {};
    dragPlace.x1 = 0;
    dragPlace.y1 = 0;
    dragPlace.x2 = 0;
    dragPlace.y2 = 0;
    
    var mediaList = new Array();
    var localUserId = "__localUser";
    mediaList[localUserId] = new UserMedia();
    var parentDiv;
    var textChat;
    // Set up audio and video regardless of what devices are present.
    var mediaConstraints = {
        'mandatory': {
            'OfferToReceiveAudio':true, 
            'OfferToReceiveVideo':true
        }
    };
    var isVideoMuted = false;
    var isAudioMuted = false;
    initialize(parentId);

    function initialize(parentId) {
        parentDiv = $("#" + parentId);
        textChat = text;
        doGetUserMedia();
    }

    function doGetUserMedia() {
        // Call into getUserMedia via the polyfill (adapter.js).
        var constraints = {
            mandatory:{},
            optional: new Array
        };
        try {
            getUserMedia({
                'audio':true, 
                'video':constraints
            }, onUserMediaSuccess,
            onUserMediaError);
            console.log("Requested access to local media with mediaConstraints:\n" +
                "  \"" + JSON.stringify(constraints) + "\"");
        } catch (e) {
            pError("该浏览器版本太旧，不支持视频聊天功能，请到<a href='http://www.google.cn/intl/zh-CN/chrome/browser/'>这里</a>下载最新版Chrome浏览器",true);
            console.log("getUserMedia failed with exception: " + e.message);
        }
    }

    function createPeerConnection(remoteUser, remoteName) {
        var pc_config = {
            iceServers: new Array()
        };
        pc_config.iceServers[0] = {
            url: "stun:stun.l.google.com:19302"
        };
        var pc_constraints = {
            optional: new Array()
        };
        try {
            // Create an RTCPeerConnection via the polyfill (adapter.js).
            mediaList[remoteUser].pc = new RTCPeerConnection(pc_config, pc_constraints);
            mediaList[remoteUser].pc.onicecandidate = function(event){
                if (event.candidate) {
                    var can = {
                        type: 'candidate',
                        label: event.candidate.sdpMLineIndex,
                        id: event.candidate.sdpMid,
                        candidate: event.candidate.candidate
                    }
                    var message = {
                        type: Request.VideoChat,
                        to: remoteUser,
                        data: JSON.stringify(can)
                    };
                    connection.sendObject(message);
                } else {
                    console.log("End of candidates.");
                }
            };
            console.log("Created RTCPeerConnnection with:\n" + 
                "  config: \"" + JSON.stringify(pc_config) + "\";\n" + 
                "  constraints: \"" + JSON.stringify(pc_constraints) + "\".");
        } catch (e) {
            console.log("Failed to create PeerConnection, exception: " + e.message);
            alert("Cannot create RTCPeerConnection object; WebRTC is not supported by this browser.");
            return null;
        }
        console.log(remoteUser);
        mediaList[remoteUser].status = "initializing";
        
        mediaList[remoteUser].pc.onconnecting = onSessionConnecting;
        mediaList[remoteUser].pc.onopen = onSessionOpened;
        mediaList[remoteUser].pc.onaddstream = function(event) {
            console.log("Remote stream added.");
            
            //addVideo(event.stream, remoteUser);
            //attachMediaStream(mediaList[remoteUser].video, event.stream);
            mediaList[remoteUser].stream = event.stream;
            addVideo(mediaList[remoteUser].stream, remoteUser, remoteName);
            waitForRemoteVideo(remoteUser); 
        };
        mediaList[remoteUser].pc.onremovestream = onRemoteStreamRemoved;
    }

    function maybeStart(remoteUser,remoteName) {
        if (!mediaList[remoteUser].pc && mediaList[localUserId].stream) {
            console.log("Creating PeerConnection.");
            createPeerConnection(remoteUser,remoteName);
            if(!mediaList[remoteUser].pc) {
                console.log("failed to create PeerConnection.");
                removeMedia(remoteUser);
                var fail = {
                    type:"fail"
                }
                var message = {
                    type: Request.VideoChat,
                    to: remoteUser,
                    data: JSON.stringify(fail)
                };
                connection.sendObject(message);
                return;
            }
            console.log("Adding local stream.");
            mediaList[remoteUser].pc.addStream(mediaList[localUserId].stream);
        // Caller initiates offer to peer.
        }
    }

    function doCall(remoteUser) {
        console.log("Sending offer to peer.");
        mediaList[remoteUser].pc.createOffer(function(sessionDescription){
            sessionDescription.sdp = preferOpus(sessionDescription.sdp);
            mediaList[remoteUser].pc.setLocalDescription(sessionDescription);
            var message = {
                type: Request.VideoChat,
                to: remoteUser,
                data: JSON.stringify(sessionDescription)
            };
            connection.sendObject(message);
        }, null, mediaConstraints);
    }

    function doAnswer(remoteUser) {
        console.log("Sending answer to peer.");
        mediaList[remoteUser].pc.createAnswer(function(sessionDescription){
            sessionDescription.sdp = preferOpus(sessionDescription.sdp);
            mediaList[remoteUser].pc.setLocalDescription(sessionDescription);
            var message = {
                type: Request.VideoChat,
                to: remoteUser,
                data: JSON.stringify(sessionDescription)
            };
            connection.sendObject(message);
        }, null, mediaConstraints);
    }

    this.processSignalingMessage = function(message) {
        var msg = JSON.parse(message.data);
        var remoteUser = message.from;
        var remoteName = message.fromName;
        if (msg.type === 'offer') {
            // Callee creates PeerConnection
            maybeStart(remoteUser, remoteName);

            mediaList[remoteUser].pc.setRemoteDescription(new RTCSessionDescription(msg));
            doAnswer(remoteUser);
        } else if (msg.type === 'answer' && mediaList[remoteUser]) {
            mediaList[remoteUser].pc.setRemoteDescription(new RTCSessionDescription(msg));
        } else if (msg.type === 'candidate' && mediaList[remoteUser]) {
            var candidate = new RTCIceCandidate({
                sdpMLineIndex:msg.label,
                candidate:msg.candidate
            });
            mediaList[remoteUser].pc.addIceCandidate(candidate);
        } else if (msg.type === 'bye' && mediaList[remoteUser]) {
            onRemoteHangup(remoteUser);
        } else if (msg.type === 'ready' && mediaList[localUserId].stream) {
            maybeStart(remoteUser,remoteName);
                    
            doCall(remoteUser);
        } else if (msg.type === 'fail') {
            onRemoteHangup(remoteUser);
        }
    }

    function onUserMediaSuccess(stream) {
        console.log("User has granted access to local media.");
        // Call the polyfill wrapper to attach the media stream to this element.
        mediaList[localUserId].stream = stream;
        addVideo(stream,localUserId,localUserId);
        var ready = {
            type:"ready"
        };
        var message = {
            type: Request.VideoChat,
            data: JSON.stringify(ready)
        };
        connection.sendObject(message);
    }

    function onUserMediaError(error) {
        console.log("Failed to get access to local media. Error code was " + error.code);
        var fail = {
            type: "fail"
        };
        var message = {
            type: Request.VideoChat,
            data: JSON.stringify(fail)
        }
        connection.sendObject(message);
    }

    function onSessionConnecting(message) {
        console.log("Session connecting.");
    }
    
    function onSessionOpened(message) {
        console.log("Session opened.");
    }
    
    function onRemoteStreamRemoved(event) {
        console.log("Remote stream removed.");
        removeMedia(remoteUser);
    }
   
    function onRemoteHangup(remoteUser) {
        console.log('Session terminated.');
        removeMedia(remoteUser);
    }
    
    function removeMedia(remoteUser) {
        console.log("remove user:" + remoteUser);
        if(mediaList[remoteUser].video) {
            mediaList[remoteUser].video.hide();
            mediaList[remoteUser].video.remove();
            mediaList[remoteUser].container.remove();
            mediaList[remoteUser].userButton.remove();
        }
        if(mediaList[remoteUser].pc) {
            mediaList[remoteUser].pc.close();
            mediaList[remoteUser].pc = null;
        }
        mediaList[remoteUser] = null;
    }

    function waitForRemoteVideo(remoteUser) {
        // Call the getVideoTracks method via adapter.js.
        console.log(mediaList[remoteUser].video.get(0).currentTime);
        videoTracks = mediaList[remoteUser].stream.getVideoTracks();
        if (videoTracks.length === 0 || mediaList[remoteUser].video.get(0).attr("currentTime") > 0) {
            transitionToActive(remoteUser);
        } else {
            setTimeout(function(){
                waitForRemoteVideo(remoteUser)
            }, 100);
        }
    }
    function transitionToActive(remoteUser) {
        console.log("begin");
        mediaList[remoteUser].video.css("opacity", 1);
        mediaList[remoteUser].status = "connected";
    }
    
    function addVideo(stream, userEmail, userName){
        var current = mediaList[userEmail];
        current.container = $("<div></div>").attr("class","container").attr("user",userEmail).css("margin","0px").height(height).width(width).hide();
        current.video = $("<video></video>").attr("user",userEmail).attr("class","video").attr("autoplay","autoplay").css("opacaity",1);
        current.video.height(height).width(width);
        attachMediaStream(current.video,current.stream);
        current.userButton.attr("class","");
        if(userEmail == localUserId){
            current.userButton.empty();
            current.userButton.html("<a tabindex='-1' href='#'>自己</a>");
            current.video.get(0).muted = true;
        } else {
            current.userButton.empty();
            current.userButton.html("<a tabindex='-1' href='#'>" + userName + "</a>");
        }
        current.userButton.click(function(){
            var u = $(this).attr("user");
            mediaList[u].container.toggle();
            
        });
        current.container.append(current.video)
        parentDiv.append(current.container);
        
        //set ability to be drag
//        current.container.draggable({containment: [
//                dragPlace.x1,dragPlace.y1,dragPlace.x2,dragPlace.y2
//        ],scroll:false});
        current.userButton.click();     
    }
    
    this.userEnter = function(user,userName){
        mediaList[user] = new UserMedia();
        var current = mediaList[user];
        current.userButton = $("<li></li>").attr("user",user).attr("class","disabled");
        if(user == localUserId){
            current.userButton.html("<a tabindex='-1' href='#'>自己(未连接)</a>");
        } else {
            current.userButton.html("<a tabindex='-1' href='#'>" + userName + "(未连接)</a>");
        }
        userListDiv.append(current.userButton);
    }
    
    this.setDragPlace = function(x1,y1,x2,y2){
        dragPlace.x1 = x1;
        dragPlace.y1 = y1;
        dragPlace.x2 = x2;
        dragPlace.y2 = y2;
        
        $(".container").draggable({containment: [
                dragPlace.x1,dragPlace.y1,dragPlace.x2,dragPlace.y2
        ],scroll:false});
    }

    function toggleVideoMute() {
        // Call the getVideoTracks method via adapter.js.
        videoTracks = mediaList[localUserId].stream.getVideoTracks();

        if (videoTracks.length === 0) {
            console.log("No local video available.");
            return;
        }

        if (isVideoMuted) {
            for (i = 0; i < videoTracks.length; i++) {
                videoTracks[i].enabled = true;
            }
            console.log("Video unmuted.");
        } else {
            for (i = 0; i < videoTracks.length; i++) {
                videoTracks[i].enabled = false;
            }
            console.log("Video muted.");
        }

        isVideoMuted = !isVideoMuted;    
    }

    function toggleAudioMute() {
        // Call the getAudioTracks method via adapter.js.
        audioTracks = mediaList[localUserId].stream.getAudioTracks();

        if (audioTracks.length === 0) {
            console.log("No local audio available.");
            return;
        }

        if (isAudioMuted) {
            for (i = 0; i < audioTracks.length; i++) {
                audioTracks[i].enabled = true;
            }
            console.log("Audio unmuted.");
        } else {
            for (i = 0; i < audioTracks.length; i++){
                audioTracks[i].enabled = false;
            }
            console.log("Audio muted.");
        }

        isAudioMuted = !isAudioMuted;  
    }

    // Ctrl-D: toggle audio mute; Ctrl-E: toggle video mute.
    // On Mac, Command key is instead of Ctrl.
    // Return false to screen out original Chrome shortcuts.
    document.onkeydown = function() {
        if (navigator.appVersion.indexOf("Mac") != -1) {
            if (event.metaKey && event.keyCode === 68) {
                toggleAudioMute();
                return false;
            }
            if (event.metaKey && event.keyCode === 69) {
                toggleVideoMute();
                return false;
            }
        } else {
            if (event.ctrlKey && event.keyCode === 68) {
                toggleAudioMute();
                return false;
            }
            if (event.ctrlKey && event.keyCode === 69) {
                toggleVideoMute();
                return false;
            }
        }
    }

    // Set Opus as the default audio codec if it's present.
    function preferOpus(sdp) {
        var sdpLines = sdp.split('\r\n');

        // Search for m line.
        for (var i = 0; i < sdpLines.length; i++) {
            if (sdpLines[i].search('m=audio') !== -1) {
                var mLineIndex = i;
                break;
            } 
        }
        if (mLineIndex === null)
            return sdp;

        // If Opus is available, set it as the default in m line.
        for (var i = 0; i < sdpLines.length; i++) {
            if (sdpLines[i].search('opus/48000') !== -1) {        
                var opusPayload = extractSdp(sdpLines[i], /:(\d+) opus\/48000/i);
                if (opusPayload)
                    sdpLines[mLineIndex] = setDefaultCodec(sdpLines[mLineIndex], opusPayload);
                break;
            }
        }

        // Remove CN in m line and sdp.
        sdpLines = removeCN(sdpLines, mLineIndex);

        sdp = sdpLines.join('\r\n');
        return sdp;
    }

    function extractSdp(sdpLine, pattern) {
        var result = sdpLine.match(pattern);
        return (result && result.length == 2)? result[1]: null;
    }

    // Set the selected codec to the first in m line.
    function setDefaultCodec(mLine, payload) {
        var elements = mLine.split(' ');
        var newLine = new Array();
        var index = 0;
        for (var i = 0; i < elements.length; i++) {
            if (index === 3) // Format of media starts from the fourth.
                newLine[index++] = payload; // Put target payload to the first.
            if (elements[i] !== payload)
                newLine[index++] = elements[i];
        }
        return newLine.join(' ');
    }

    // Strip CN from sdp before CN constraints is ready.
    function removeCN(sdpLines, mLineIndex) {
        var mLineElements = sdpLines[mLineIndex].split(' ');
        // Scan from end for the convenience of removing an item.
        for (var i = sdpLines.length-1; i >= 0; i--) {
            var payload = extractSdp(sdpLines[i], /a=rtpmap:(\d+) CN\/\d+/i);
            if (payload) {
                var cnPos = mLineElements.indexOf(payload);
                if (cnPos !== -1) {
                    // Remove CN payload from m line.
                    mLineElements.splice(cnPos, 1);
                }
                // Remove CN line in sdp
                sdpLines.splice(i, 1);
            }
        }

        sdpLines[mLineIndex] = mLineElements.join(' ');
        return sdpLines;
    }
    
    this.getWidth = function(){
        return width;
    }
    
    this.getHeight = function(){
        return height;
    }
    
    this.setDraggable = function(){
        $(".container").draggable('enable');
    }
    
    this.setUndraggable = function(){
        $(".container").draggable('disable');
    }
}