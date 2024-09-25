package enums;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("ROLE_ADMIN") {
        @Override
        public String getDescricao() {
            return "Acesso total ";
        }
    },

    TECNICO("ROLE_TECNICO") {
        @Override
        public String getDescricao() {
            return "Realiza apontamentos mas não visualiza valores de itens e nem tem acesso a medição";
        }
    },
    ENGENHEIRO("ROLE_ENGENHEIRO") {
        @Override
        public String getDescricao() {
            return "Autoriza medições e acessa todos os itens";
        }
    },
    COORDENADOR("ROLE_COORDENADOR") {
        @Override
        public String getDescricao() {
            return "Gerencia engenheiros e técnicos";
        }
    },
    AUDITOR("ROLE_AUDITOR") {
        @Override
        public String getDescricao() {
            return "Tem acesso completo para auditoria";
        }
    };

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public abstract String getDescricao();

    public static Role fromRoleName(String roleName) {
        for (Role role : Role.values()) {
            if (role.getRoleName().equals(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role name: " + roleName);
    }
}
