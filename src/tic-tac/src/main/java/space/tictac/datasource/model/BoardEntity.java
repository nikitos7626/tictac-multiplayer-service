package space.tictac.datasource.model;

import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import space.tictac.datasource.converter.BoardConverter;

@Embeddable
public class BoardEntity {

    @Convert(converter = BoardConverter.class)
    private int [][]arr;

    public int [][]getArr(){
        return this.arr;
    }

    public void setArr(int [][]arr){
        this.arr=arr;
    }

    public BoardEntity(){
        this.arr=new int[3][3];
    }

    public BoardEntity(int [][]arr){
        this.arr =arr;
    }
}
