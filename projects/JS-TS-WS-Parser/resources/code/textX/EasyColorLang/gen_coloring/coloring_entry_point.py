from textx import language, generator
from .generators import load_metamodel
from .generators.textmate_generator import TextMateGrammarGenerator
from .utils import dump_to_file


@language('EasyColorLang', '*.eclr')
def easy_coloring_lang():
    """Language made for easier writing of TextMate grammars"""
    return load_metamodel()


@generator('EasyColorLang', 'TextMate')
def textmate_gen_coloring(metamodel, model, output_path, overwrite, debug,
                          **custom_args):
    """Generator for TextMate syntax grammar from EasyColorLang spec"""
    ret_val = TextMateGrammarGenerator(model).generate()
    if not output_path:
        print(ret_val)
    else:
        dump_to_file(ret_val, output_path)
    return ret_val
