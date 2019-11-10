package LepinskiEngine;
//A Robot's ID can be any integer
//The ID is used to distinguish betwen robots with the same Model

public class Robot{
    ModelType model;
    int ID;

    public ModelType getModel(){
	return model;
    }

    public int getID(){
	return ID;
    }

    public Robot(ModelType the_model, int the_id){
	model = the_model;
	ID = the_id;
    }

}