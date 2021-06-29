package app.mapreduce;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;
import de.othr.vs.xml.Veranstaltung;

import java.util.Date;

public class TippMapper implements Mapper<String, Veranstaltung, String, Veranstaltung> {

    private String[] suchwoerter;

    public TippMapper(String[] suchwoerter) {
        this.suchwoerter = suchwoerter;
    }
    @Override
    public void map(String s, Veranstaltung veranstaltung, Context<String, Veranstaltung> context) {
        if (veranstaltung.getEnde().before(new Date()))
            return;

        for (String sw: this.suchwoerter) {
            if (veranstaltung.getTitel().contains(sw) || veranstaltung.getBeschreibung().contains(sw))
                context.emit(sw, veranstaltung);
        }
    }
}
