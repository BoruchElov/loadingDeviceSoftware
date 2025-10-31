package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

public class FunForScenariev {

    /**
     * Класс в котором лежат функции для отправки команд инвертору от элементов форм.
     * TODO:p1 Есть идея написать общий метод для прохождения проверок на форме 100 и 101. Типа запустить в потоке одну функцию при нажатии кнопки старт
     */
        private FunForScenariev() {
        }
        /**
         *Функция, которая опрашивает инвертор. Задача функции понять готов ли инвертор работать.
         *Функция должна вернуть True/False и в зависимости от этого будет определяться статус проверки
         */
        public static boolean getStatusInvertor(){
            System.out.println("getStatusInvertor");
            return true;
        }

        /**
         * Отправляет команду инвертору проверить свое питание на выходе.
         * Ждет ответа. Если напряжение соответствует уставке. True и идем дальше
         */
        public static boolean getPowerStatusInvertor() {
            System.out.println("getPowerStatusInvertor");
            return true;
        }

        /**
         * Опрашивает инвертор на наличие синхронизации
         * Если все выбранные модули синхронизированы True.
         * TODO:p3 в данный момент синхронизация не готова. Нужно предусмотреть прохождение этой проверки без проверки синхронизации
         * (Думаю необходимо сделать заглушку, которая бы опросила инвертор по типу
         * функции @getStatusInvertor и пошла дальше)
         */
        public static boolean getStatusSynchronousInvertor(){
            System.out.println("getStatusSynchronousInvertor");
            return true;
        }

        /**
         * Опрашивает инвертор на состояние положения кнопки трансформатора.
         * Если введенный ток в форме соответствует выбранному на трансформаторе диапазону, то True и идем дальше
         *
         */
        public static boolean getStatusModeCurrent(){
            System.out.println("getStatusModeCurrent");
            return true;
        }

        /**
         *Запросить у инвертора проверку силовых проводов. Если проверка успешная, то True
         *==============================
         *Данная проверка является сложной так как во время этой проверки выдается большой ток.
         * Необходимо иметь возможность применить функцию остановки выдачи тока STOP_SCENARIO
         */
        public static boolean getStatusResistCheck(){
            System.out.println("getStatusResistCheck");
            return true;
        }
        /**Данная функция передает инвертору все параметры для его работы и переводит его в режим ожидания старта сценария.
         * После принятия данных инвертор отдаст данные по готовности к работе
         */
        public boolean SET_SCENARIO_PARAMETRS(){
            System.out.println("SET_SCENARIO_PARAMETRS");
            return true;
        }

        /**Запускает сценарий выбранной формы
         *
         */
        public boolean START_SCENARIO() {
            System.out.println("START_SCENARIO");
            return true;
        }

        /**Принудительно останавливает сценарий
         *
         */
        public boolean STOP_SCENARIO () {
            System.out.println("STOP_SCENARIO ");
            return true;
        }

        /**запрашивает данные у инвертора о токах на выводах, напряжении на выводах, углах токов, времени,
         * тока и напряжения на измерителях. В ходе режима обновляет их на форме
         *
         * TODO:p1 Проблема заключается в том, что опрпашивать 6 инверторов и синхронизировать их очень тяжело, эфир будет засорен
         * Поэтому тут еще все под вопросом. Обновлять параметры токов АМ  и ВМ можно, а на форме нужно будет еще подумать чуть позже после синхронизации.
         */
        public void getDataScenario(){
        }

        //Общая функция всех проверок
        public static void Check_Condition_Start(){
            getStatusInvertor();
            getPowerStatusInvertor();
            getStatusSynchronousInvertor();
            getStatusModeCurrent();
            getStatusResistCheck();
        }
    }

