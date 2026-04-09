package lox;

public class AstRpnPrinter implements Expr.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override 
    public String visitBinaryExpr(Expr.Binary expr) {
        return expr.left.accept(this) + " " + expr.right.accept(this) + " " + expr.operator.lexeme;
    }
    
    @Override 
    public String visitGroupingExpr(Expr.Grouping expr) {
        return expr.expression.accept(this) + "group";
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return expr.operator.lexeme + expr.right.accept(this);
    }

    public static void main(String[] args) {
        Expr expression1 = new Expr.Binary(new Expr.Literal(1),
                            new Token(TokenType.PLUS, "+", null, 1),
                            new Expr.Literal(2));

        Expr expression2 = new Expr.Binary(new Expr.Literal(4),
                            new Token(TokenType.MINUS, "-", null, 1),
                            new Expr.Literal(3));

        Expr expression = new Expr.Binary(expression1, new Token(TokenType.STAR, "*", null, 1), expression2);
        System.out.println(new AstRpnPrinter().print(expression));
    }
}
