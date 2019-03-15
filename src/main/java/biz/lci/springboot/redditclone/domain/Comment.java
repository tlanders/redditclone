package biz.lci.springboot.redditclone.domain;

import biz.lci.springboot.redditclone.service.BeanUtil;
import lombok.*;
import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Comment extends Auditable {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String body;

    @NonNull
    @ManyToOne
    private Link link;

    public String getPrettyTime() {
        return BeanUtil.getBean(PrettyTime.class).format(convertToDateViaInstant(getCreationDate()));
    }

    protected Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }
}
