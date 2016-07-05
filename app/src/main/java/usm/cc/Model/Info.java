package usm.cc.Model;

/**
 * Created by niko on 25/05/2016.
 */
public class Info {

    String text;
    String value;

    public Info(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
