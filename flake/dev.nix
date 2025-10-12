{
    perSystem =
        { pkgs, ... }:
        {
            devShells.default = pkgs.mkShell {
                packages = with pkgs; [
                    nushell
                    gradle
                ];

                shellHook = ''
                    exec nu
                '';
            };
        };
}
