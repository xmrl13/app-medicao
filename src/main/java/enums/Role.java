package enums;

public enum Role {
    TECNICO("ROLE_TECNICO", "Realiza apontamentos mas não visualiza valores de itens e nem tem acesso a medição"),
    ENGENHEIRO("ROLE_ENGENHEIRO", "Autoriza medições e acessa todos os itens"),
    COORDENADOR("ROLE_COORDENADOR", "Gerencia engenheiros e técnicos"),
    AUDITOR("ROLE_AUDITOR", "Tem acesso completo para auditoria");

    private final String roleName;
    private final String descricao;

    Role(String roleName, String descricao) {
        this.roleName = roleName;
        this.descricao = descricao;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método estático para obter o Role a partir do roleName
    public static Role fromRoleName(String roleName) {
        for (Role role : Role.values()) {
            if (role.getRoleName().equals(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role name: " + roleName);
    }
}
