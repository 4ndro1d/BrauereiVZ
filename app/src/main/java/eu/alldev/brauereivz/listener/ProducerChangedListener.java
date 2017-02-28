package eu.alldev.brauereivz.listener;

import java.util.List;

import eu.alldev.brauereivz.model.Producer;

public interface ProducerChangedListener {

    void producersChanged(List<Producer> entities);
}
