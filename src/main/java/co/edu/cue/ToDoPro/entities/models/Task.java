package co.edu.cue.ToDoPro.entities.models;

import co.edu.cue.ToDoPro.entities.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private int id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private TaskStatus status;

}
