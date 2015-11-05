package planeGame;

public class VariableImg extends Animation {
	
	public VariableImg(String animationType, int x, int y) {
        images = animations.get(animationType);
        this.currImage = 0;
        framesSinceInit = 1;
        //numFramesPerImg = 3;
        img = images.elementAt(currImage);
        numOfImages = images.size();
        this.x = x;
        this.y = y;
        //this.speed = 0;
    }

	
	public void update(boolean nextImg) {
		if(currImage < numOfImages ){
			framesSinceInit++;
			//System.out.println(currImage);
			img = images.elementAt(currImage);
			if(nextImg){
				currImage++;
			}
			
		}else{
			img = null;
		}
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	

}
