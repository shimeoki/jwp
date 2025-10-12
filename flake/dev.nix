{ inputs, ... }:
{
    perSystem =
        { pkgs, ... }:
        {
            devShells.default = pkgs.mkShell {
                packages = with pkgs; [
                    nushell
                    jdk25
                ];

                shellHook = ''
                    exec nu --execute "alias gw = ${inputs.self}/gradlew"
                '';
            };
        };
}
