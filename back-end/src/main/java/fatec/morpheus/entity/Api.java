package fatec.morpheus.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Api")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "api_cod")
    private int code;

    @Column(name = "api_url", length = 500, unique = true)
    @Size(max = 150, message = "API Address cannot exceed 150 characters")
    private String address;

    @Column(name = "api_name", length = 500, unique = false)
    @Size(max = 150, message = "API Address cannot exceed 150 characters")
    private String name;

    @Column(name = "api_post")
    @Min(value = 0, message = "Post value must be 0 or 1")
    @Max(value = 1, message = "Post value must be 0 or 1")
    private int post;
    
    @Column(name = "api_get")
    @Min(value = 0, message = "Get value must be 0 or 1")
    @Max(value = 1, message = "Get value must be 0 or 1")
    private int get;


    @Transient
    public List<Integer> getTagCodes() {
        return tagCodes;
    }

    @JsonIgnore
    @ElementCollection
    @CollectionTable(name = "Api_tag", joinColumns = @JoinColumn(name = "src_cod"))
    @Column(name = "tag_cod")
    private List<Integer> tagCodes;

    public void setTagCodes(List<Integer> tagCodes) {
        if (tagCodes != null) {
            this.tagCodes = new ArrayList<>(tagCodes);
        } else {
            this.tagCodes = new ArrayList<>();
        }
    }
}
