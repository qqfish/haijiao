//the type of request and response in web socket
//

var Request = {
	SavePage: 0,
	TmpShape: 1,
	DrawShape: 2,
        EraseShape: 3,
        VideoChat: 4,
        TextChat: 5,
        ChangePage : 6,
        ChangeFile : 7,
        AddFileFromUser : 8,
        UploadFile: 9,
        ToggleTimer: 10,
        StopTimer: 11,
        DownloadPdf: 12
}

var Response = {
    TmpShape: 0,
    DrawShape: 1,
    EraseShape: 2,
    VideoChat: 3,
    TextChat: 4,
    ChangePage : 5,
    ChangeBookmark : 6,
    AddRoomFile : 7,
    SetUserFile : 8,
    UploadBackground : 9,
    ShowTimer : 10,
    UserEnter : 11,
    Error: 100,
    Info: 101
}

var ErrorType = {
    AddFileFromUser: 0,
    TimerNoPermission: 1,
    NoStudentEnter: 2
}

var InfoType = {
    ClazzFinish: 0,
    SomeoneEnter: 1,
    SomeoneExit: 2
}
