package io.github.shimeoki.jwp.app.actions.tagdelete;

import io.github.shimeoki.jwp.app.Provider;
import io.github.shimeoki.jwp.domain.repositories.TagRepository;

public interface DeleteTagProvider extends Provider {

    TagRepository tagRepository();
}
