package me.tonoy.jugtoursapi;

import me.tonoy.jugtoursapi.group.Event;
import me.tonoy.jugtoursapi.group.Group;
import me.tonoy.jugtoursapi.group.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {
    private final GroupRepository groupRepository;

    public Initializer(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Seattle JUG", "Denver JUG", "Dublin JUG", "London JUG").forEach(name -> {
                    var group = new Group();
                    group.setName(name);
                    groupRepository.save(group);
                }
        );
        var group = groupRepository.findByName("Seattle JUG");
        var event = new Event();
        event.setTitle("Micro Frontends for Java Developers");
        event.setDescription("JHipster now has microfrontend support!");
        event.setDate(Instant.parse("2022-09-13T17:00:00.000Z"));
        group.setEvents(Collections.singleton(event));

        groupRepository.save(group);

        groupRepository.findAll().forEach(System.out::println);
    }
}
