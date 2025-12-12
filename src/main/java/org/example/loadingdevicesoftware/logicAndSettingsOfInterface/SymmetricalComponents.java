package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import org.apache.commons.math3.complex.Complex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;


public class SymmetricalComponents {

    private SymmetricalComponents() {
    }

    // a = e^{+j120°}
    private static final Complex A =
            new Complex(-0.5, Math.sqrt(0.75));

    // a^2 = e^{-j120°}
    private static final Complex A2 =
            new Complex(-0.5, -Math.sqrt(0.75));

    /**
     * Метод для перевода комплексного числа из показательной формы в алгебраическую
     * @param magnitude модуль комплексного числа
     * @param angleDeg аргумент комплексного числа в градусах
     * @return
     */
    private static Complex fromPolar(double magnitude, double angleDeg) {
        double rad = Math.toRadians(angleDeg);
        return new Complex(
                magnitude * Math.cos(rad),
                magnitude * Math.sin(rad)
        );
    }

    /**
     * Метод для перевода комплексного числа из алгебраической формы в показательную
     * @param c комплексное число
     * @return
     */
    private static double[] toMagAngle(Complex c) {
        return new double[]{
                round(c.abs()),
                round(Math.toDegrees(c.getArgument()))
        };
    }

    private static double round(double value) {
        return BigDecimal
                .valueOf(value)
                .setScale(1, RoundingMode.FLOOR)
                .doubleValue();
    }

    /**
     * Метод для получения массива векторов симметричных составляющих в показательной форме из значений фазных величин
     * в показательной форме
     * @param magA модуль вектора фазы А
     * @param angA аргумент вектора фазы А
     * @param magB модуль вектора фазы В
     * @param angB аргумент вектора фазы В
     * @param magC модуль вектора фазы С
     * @param angC аргумент вектора фазы С
     * @return
     */
    public static double[] phaseToSymmetrical(
            double magA, double angA,
            double magB, double angB,
            double magC, double angC) {

        Complex UA = fromPolar(magA, angA);
        Complex UB = fromPolar(magB, angB);
        Complex UC = fromPolar(magC, angC);

        Complex U0 = UA.add(UB).add(UC).divide(3.0);
        Complex U1 = UA.add(A.multiply(UB)).add(A2.multiply(UC)).divide(3.0);
        Complex U2 = UA.add(A2.multiply(UB)).add(A.multiply(UC)).divide(3.0);

        double[] res0 = toMagAngle(U0);
        double[] res1 = toMagAngle(U1);
        double[] res2 = toMagAngle(U2);

        return new double[]{
                res0[0], res0[1],
                res1[0], res1[1],
                res2[0], res2[1]
        };
    }

    /**
     * Метод для получения массива векторов фазных векторов в показательной форме из значений симметричных составляющих
     * в показательной форме
     * @param mag0 модуль вектора нулевой последовательности
     * @param ang0 аргумент вектора нулевой последовательности
     * @param mag1 модуль вектора прямой последовательности
     * @param ang1 аргумент вектора прямой последовательности
     * @param mag2 модуль вектора обратной последовательности
     * @param ang2 аргумент вектора прямой последовательности
     * @return
     */
    public static double[] symmetricalToPhase(
            double mag0, double ang0,
            double mag1, double ang1,
            double mag2, double ang2) {

        Complex U0 = fromPolar(mag0, ang0);
        Complex U1 = fromPolar(mag1, ang1);
        Complex U2 = fromPolar(mag2, ang2);

        Complex UA = U0.add(U1).add(U2);
        Complex UB = U0.add(A2.multiply(U1)).add(A.multiply(U2));
        Complex UC = U0.add(A.multiply(U1)).add(A2.multiply(U2));

        double[] resA = toMagAngle(UA);
        double[] resB = toMagAngle(UB);
        double[] resC = toMagAngle(UC);

        return new double[]{
                resA[0], resA[1],
                resB[0], resB[1],
                resC[0], resC[1]
        };
    }

}
