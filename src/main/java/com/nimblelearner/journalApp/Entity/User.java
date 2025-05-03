package com.nimblelearner.journalApp.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users" , indexes = @Index(columnList = "userName",unique = true))
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    @NonNull
    private String userName;

    @NonNull
    private String password;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<JournalEntry> journalEntries = new ArrayList<>();

    private List<String> roles = new ArrayList<>();

}
