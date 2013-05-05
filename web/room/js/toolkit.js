//class toolkit
//

var Tooltype = {
	Hand: 0,
	Pen: 1,
	Circle: 2,
	Line:  3,
	Eraser: 4
}


function Toolkit(){
	//private
	var tool = Tooltype.Hand;
	var color = "black";
	var width = 5;
        var alpha = 1;
	//tooltype:
	//hand : move the paper
	//pen
	//circle
	//line
	//eraser
	
	this.changeTool = function(tooltype){
		tool = tooltype;
	}

	this.getTool = function(){
		return tool;
	}

	this.changeColor = function(c){
		color = c;
	}

	this.getColor = function(){
		return color;
	}

	this.changeWidth = function(w){
		width = w;
	}

	this.getWidth = function(){
		return width;
	}
        
        this.changeAlpha = function(a){
            alpha = a;
        }
        
        this.getAlpha = function(a) {
            return alpha;
        }
}