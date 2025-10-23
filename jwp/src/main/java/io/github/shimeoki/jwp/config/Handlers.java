package io.github.shimeoki.jwp.config;

import io.github.shimeoki.jwp.app.actions.tagcreate.CreateTagHandler;
import io.github.shimeoki.jwp.app.actions.tagdelete.DeleteTagHandler;
import io.github.shimeoki.jwp.app.actions.taglist.ListTagsHandler;
import io.github.shimeoki.jwp.app.actions.tagrename.RenameTagHandler;

public record Handlers(
        CreateTagHandler createTag,
        DeleteTagHandler deleteTag,
        ListTagsHandler listTags,
        RenameTagHandler renameTag) {
}
