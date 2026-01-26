package org.firstinspires.ftc.teamcode.libs.util;

import java.util.HashMap;
import java.util.Iterator;

public class Scheduler {

    private final HashMap<String, Event> event_map = new HashMap<>();

    public Scheduler(){

    }

    public Event schedule_event(long millis, Runnable runnable){

        //creates new event based on parameter

        String ID = Event.randomID(5);

        while(event_map.containsKey(ID)){

            ID = Event.randomID(5);

        }

        Event event = new Event(millis, ID, runnable);

        event_map.put(ID, event);

        return event;

    }

    public Event schedule_event(long millis, String ID, Runnable runnable) {

        if (!event_map.containsKey(ID)) {

            Event event = new Event(millis, ID, runnable);

            event_map.put(ID, event);

            return event;

        }

        return null;

    }

    public Event set_event(Long millis, String ID, Runnable runnable) {

        if(ID == null){

            return schedule_event(millis, runnable);

        }

        Event event = get_event(ID);

        if (event != null) {

            cancel(ID);

            long new_millis = millis == null ? event.getStopwatch().remaining_nano_time() / 1_000_000 : millis;
            Runnable new_runnable = runnable == null ? event.getEvent() : runnable;

            return schedule_event(new_millis, ID, new_runnable);

        }

        return schedule_event(millis, ID, runnable);

    }

    public Event get_event(String ID) {

        return event_map.get(ID);

    }

    public void cancel(String ID) {
        
        event_map.remove(ID);
        
    }

    public void loop() {

        Iterator<Event> iterator = event_map.values().iterator();

        while(iterator.hasNext()){

            Event event = iterator.next();

            if (event.getStopwatch().is_timer_done()){

                iterator.remove();
                event.run();


            }
        }

    }

}
