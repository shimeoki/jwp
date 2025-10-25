{ inputs, ... }:
{
    imports = [
        inputs.treefmt.flakeModule
    ];

    perSystem = {
        treefmt = {
            programs = {
                # keep-sorted start block=yes newline_separated=yes
                keep-sorted = {
                    enable = true;
                };

                nixfmt = {
                    enable = true;
                    width = 80;
                    indent = 4;
                };
                # keep-sorted end
            };
        };
    };
}
