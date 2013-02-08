/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function UserMedia(){
    this.pc = null;
    this.video = null;
    this.remoteStream = null;
    this.status = null;
}
function Media(parentId, text){
    var mediaList = new Array();
    var localVideo;
    var localStream;
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
        localVideo = $("<video></video>").attr("user","_local").attr("class","video");
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
            alert("getUserMedia() failed. Is this a WebRTC capable browser?");
            console.log("getUserMedia failed with exception: " + e.message);
        }
    }

    function createPeerConnection(remoteUser) {
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
        
        mediaList[remoteUser].status = "initializing";
        mediaList[remoteUser].video = $("<video></video>").attr("user",remoteUser).attr("class","video").hide();

        mediaList[remoteUser].pc.onconnecting = onSessionConnecting;
        mediaList[remoteUser].pc.onopen = onSessionOpened;
        mediaList[remoteUser].pc.onaddstream = function(event) {
            console.log("Remote stream added.");
            
            attachMediaStream(mediaList[remoteUser].video, event.stream);
            mediaList[remoteUser].remoteStream = event.stream;
            waitForRemoteVideo(); 
        };
        mediaList[remoteUser].pc.onremovestream = onRemoteStreamRemoved;
    }

    function maybeStart(remoteUser) {
        if (!mediaList[remoteUser] && localStream) {
            mediaList[remoteUser] = new UserMedia();
            console.log("Creating PeerConnection.");
            createPeerConnection(remoteUser);
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
            mediaList[remoteUser].pc.addStream(localStream);
        // Caller initiates offer to peer.
        }
    }

    this.doCall = function(remoteUser) {
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
        if (msg.type === 'offer') {
            // Callee creates PeerConnection
            if (!mediaList[remoteUser])
                maybeStart(remoteUser);

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
        } else if (msg.type === 'ready' && localStream) {
            if (!mediaList[remoteUser])
                maybeStart(remoteUser);
                    
            doCall(remoteUser);
        } else if (msg.type === 'fail') {
            onRemoteHangup(remoteUser);
        }
    }

    function onUserMediaSuccess(stream) {
        console.log("User has granted access to local media.");
        // Call the polyfill wrapper to attach the media stream to this element.
        attachMediaStream(localVideo, stream);
        addVideo(localVideo);
        localVideo.css("opacity",1);
        localStream = stream;
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
    }
   
    function onRemoteHangup(remoteUser) {
        console.log('Session terminated.');
        removeMedia(remoteUser);
    }
    
    function removeMedia(remoteUser) {
        if(mediaList[remoteUser].pc) {
            mediaList[remoteUser].pc.close();
        }
        if(mediaList[remoteUser].video) {
            removeVideo(mediaList[remoteUser].video);
        }
        mediaList[remoteUser] = null;
    }

    function waitForRemoteVideo(remoteUser) {
        // Call the getVideoTracks method via adapter.js.
        videoTracks = mediaList[remoteUser].remoteStream.getVideoTracks();
        if (videoTracks.length === 0 || mediaList[remoteUser].remoteStream.currentTime > 0) {
            transitionToActive(remoteUser);
        } else {
            setTimeout(function(){
                waitForRemoteVideo(remoteUser)
                }, 100);
        }
    }
    function transitionToActive(remoteUser) {
        mediaList[remoteUser].video.css("opacity", 1);
        mediaList[remoteUser].status = "connected";
        addVideo(mediaList[remoteUser].video);
    }
    
    function addVideo(video){
        parentDiv.append(video);
    }
    
    function removeVideo(video){
        video.hide();
        video.remove();
    }

    function toggleVideoMute() {
        // Call the getVideoTracks method via adapter.js.
        videoTracks = localStream.getVideoTracks();

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
        audioTracks = localStream.getAudioTracks();

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
    
    
    //adapter
    var RTCPeerConnection = null;
    var getUserMedia = null;
    var attachMediaStream = null;

    if (navigator.mozGetUserMedia) {
        console.log("This appears to be Firefox");

        // The RTCPeerConnection object.
        RTCPeerConnection = mozRTCPeerConnection;

        // Get UserMedia (only difference is the prefix).
        // Code from Adam Barth.
        getUserMedia = navigator.mozGetUserMedia.bind(navigator);

        // Attach a media stream to an element.
        attachMediaStream = function(element, stream) {
            console.log("Attaching media stream");
            element.attr("mozSrcObject",stream);
            element.play();
        };
    } else if (navigator.webkitGetUserMedia) {
        console.log("This appears to be Chrome");

        // The RTCPeerConnection object.
        RTCPeerConnection = webkitRTCPeerConnection;
  
        // Get UserMedia (only difference is the prefix).
        // Code from Adam Barth.
        getUserMedia = navigator.webkitGetUserMedia.bind(navigator);

        // Attach a media stream to an element.
        attachMediaStream = function(element, stream) {
            element.attr("src",createObjectURL(stream));
        };

        // The representation of tracks in a stream is changed in M26.
        // Unify them for earlier Chrome versions in the coexisting period.
        if (!webkitMediaStream.prototype.getVideoTracks) {
            webkitMediaStream.prototype.getVideoTracks = function() {
                return this.videoTracks;
            }
        }
        if (!webkitMediaStream.prototype.getAudioTracks) {
            webkitMediaStream.prototype.getAudioTracks = function() {
                return this.audioTracks;
            }
        }
    } else {
        console.log("Browser does not appear to be WebRTC-capable");
    }
}