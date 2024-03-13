package javadesignpatterns.mvc;

public class GiantController {

	private final GiantModel giant;
	private final GiantView view;

	public GiantController(GiantModel giant, GiantView view) {
		this.giant = giant;
		this.view = view;
	}

	@SuppressWarnings("UnusedReturnValue")
	public Health getHealth() {
		return giant.getHealth();
	}

	public void setHealth(Health health) {
		this.giant.setHealth(health);
	}

	@SuppressWarnings("UnusedReturnValue")
	public Fatigue getFatigue() {
		return giant.getFatigue();
	}

	public void setFatigue(Fatigue fatigue) {
		this.giant.setFatigue(fatigue);
	}

	@SuppressWarnings("UnusedReturnValue")
	public Nourishment getNourishment() {
		return giant.getNourishment();
	}

	public void setNourishment(Nourishment nourishment) {
		this.giant.setNourishment(nourishment);
	}

	public void updateView() {
		this.view.displayGiant(giant);
	}
}