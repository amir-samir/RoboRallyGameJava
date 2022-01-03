package game;

import lombok.Data;

/**
 * @author: yiluye ,chen
 * @Date: 2021/12/29
 */
@Data

public class Robot {
    private int x;
    private int y;
    private String direction;

    // 0→ 1← 2↑ 3↓
    public void forward(int step) {
        switch (this.direction) {
            case "0":
                x += step;
                break;
            case "1":
                x -= step;
                break;
            case "2":
                y += step;
                break;
            case "3":
                y -= step;
                break;
        }
    }

    public void backup(int step) {
        switch (this.direction) {
            case "0":
                x -= step;
                break;
            case "1":
                x += step;
                break;
            case "2":
                y -= step;
                break;
            case "3":
                y += step;
                break;
        }
    }
    public void Opposite() {
        switch(this.direction)
        {
            case "0" :
                this.direction = "1" ;
                break;
            case "3" :
                this.direction = "2";
                break;
            case "1" :
                this.direction = "0";
                break;
            case "2" :
                this.direction = "3";
                break;
        }
    }

    public void RIGHT()
    {
        switch(this.direction)
        {
            case "0" :
                this.direction= "3";
                break;
            case "3":
                this.direction= "1";
                break;
            case "1":
                this.direction= "2";
                break;
            case "2" :
                this.direction= "0";
                break;
        }
    }

    public void LEFT()
    {
        switch(this.direction)
        {
            case "0" :
                this.direction= "2";
                break;
            case "3" :
                this.direction= "0";
                break;
            case "1" :
                this.direction= "3" ;
                break;
            case "2" :
                this.direction= "1";
                break;
        }
    }

}
