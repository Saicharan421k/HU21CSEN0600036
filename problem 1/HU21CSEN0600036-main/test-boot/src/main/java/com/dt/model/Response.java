package com.dt.model;

import java.util.ArrayList;
import java.util.List;

public class Response {
        private  List<Integer> numbers;
        private  List<Integer> windowPrevState;
        private  List<Integer> windowCurrState;
        private  double avg;
        
        public Response() {
            
        }


        public Response(String error, List<Integer> windowPrevState, List<Integer> windowCurrState, double avg) {
            this.numbers = error != null ? new ArrayList<>() : null;
            this.windowPrevState = windowPrevState;
            this.windowCurrState = windowCurrState;
            this.avg = avg;
        }

        public Response(List<Integer> numbers, List<Integer> windowPrevState, List<Integer> windowCurrState, double avg) {
            this.numbers = numbers;
            this.windowPrevState = windowPrevState;
            this.windowCurrState = windowCurrState;
            this.avg = avg;
        }

        public List<Integer> getNumbers() {
            return numbers;
        }

        public List<Integer> getWindowPrevState() {
            return windowPrevState;
        }

        public List<Integer> getWindowCurrState() {
            return windowCurrState;
        }

        public double getAvg() {
            return avg;
        }

    public void setNumbers(Response response) {
    this.numbers = numbers;
        }
}