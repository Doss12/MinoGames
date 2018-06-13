package View;

import com.badlogic.gdx.Input.TextInputListener;

public class JoueurListener implements TextInputListener {
String text;
	@Override
	public void input(String text) {
		
		System.out.println(text);
		
	}

	@Override
	public void canceled() {
		System.out.println("");
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	

}
