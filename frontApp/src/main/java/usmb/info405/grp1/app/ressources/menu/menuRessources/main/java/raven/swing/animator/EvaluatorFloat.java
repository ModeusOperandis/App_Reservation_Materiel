package usmb.info405.grp1.app.ressources.menu.menuRessources.main.java.raven.swing.animator;

public class EvaluatorFloat extends Evaluator<Float> {

    @Override
    public Float evaluate(Float from, Float target, float fraction) {
        return from + ((target - from) * fraction);
    }
}
